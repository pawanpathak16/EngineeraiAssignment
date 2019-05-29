package com.assignment.engineeraiassignment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.assignment.engineeraiassignment.AppConstants;
import com.assignment.engineeraiassignment.R;
import com.assignment.engineeraiassignment.data.remote.api.PostsApiResponse;
import com.assignment.engineeraiassignment.factory.ViewModelFactory;
import com.assignment.engineeraiassignment.model.Post;
import com.assignment.engineeraiassignment.ui.adaptor.PostAdaptor;
import com.assignment.engineeraiassignment.viewmodel.Hits;
import com.assignment.engineeraiassignment.viewmodel.PostsViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

import static com.assignment.engineeraiassignment.data.remote.api.Status.ERROR;
import static com.assignment.engineeraiassignment.data.remote.api.Status.SUCCESS;

public class HomeActivity  extends AppCompatActivity implements Callback
{
    int count= 0;
    int page=1;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.my_toolbar)
    Toolbar mToolBar;
    @BindView(R.id.count)
    TextView toolbarTextView_count;




    PostAdaptor mPostAdaptor;
    List<Post> mPostList=new ArrayList<>();

    @Inject
    Gson gson;

    @Inject
    ViewModelFactory viewModelFactory;
    PostsViewModel viewModel;


    private int visibleThreshold = 9;
    private int lastVisibleItem, totalItemCount;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        toolbarTextView_count.setText(Integer.toString(count));
       // AppConstants.BASE_URL="https://hn.algolia.com/api/v1/";


        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mPostAdaptor =new PostAdaptor(mPostList);
        mPostAdaptor.setListener(HomeActivity.this);
        mRecyclerView.setAdapter(mPostAdaptor);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));



        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel.class);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel.class);


        viewModel.weatherResponse().observe(this, this::consumeResponse);



        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               mPostList.clear();
                viewModel.getPostsByPage(page);

            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                System.out.println("dx "+dx);
                System.out.println("dy"+dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                System.out.println("total item count "+totalItemCount);
                System.out.println("last visible item "+lastVisibleItem);

                if(totalItemCount <= lastVisibleItem+1)
                {
                    System.out.println("load more calling ");
                    page++;
                    loadmore();
                }

            }
        });



    }

    private void loadmore()
    {
    viewModel.getPostsByPage(page);
    }

    @Override
    protected void onResume() {
        super.onResume();


            viewModel.getPostsByPage(1);

    }

    public  void consumeResponse(PostsApiResponse apiResponse)
    {
        switch (apiResponse.status) {

            case LOADING:
                // progressDialog.show();
                break;

            case SUCCESS:
                try {
                    System.out.println("---->" + apiResponse.data);
                    Hits posts = gson.fromJson(apiResponse.data, Hits.class);
                    System.out.println("--->hello" + posts.getHits().size());
                    updateUi(posts.getHits());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

               // Weather weather = gson.fromJson(apiResponse.data, Weather.class);

               // System.out.println("current weather "+weather.getHourly().getData().get(1).getSummary()+"--"+weather.getHourly().getData().get(1).getIcon());
               // System.out.println("Hourly weather ======>"+weather.getHourly().getData().get(5).getSummary());
               // updateWeatherUi(weather);


                //

                // renderSuccessResponse(apiResponse.data);
                break;

            case ERROR:
                Log.e("error",apiResponse.error.getMessage());
                // progressDialog.dismiss();
                // Toast.makeText(LoginActivity.this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }

    private void updateUi(List<Post> hits)
    {
       // mPostList.clear();
        if(!mPostList.isEmpty())
        {
            for (Post post : hits) {
                mPostList.add(post);
            }
        }
        else{
            for (Post post : hits) {
                mPostList.add(post);
            }
            if(mSwipeRefreshLayout.isRefreshing())
            {
                mSwipeRefreshLayout.setEnabled(false);

            }
        }

        mPostAdaptor.notifyDataSetChanged();


       // mPostAdaptor.notifyDataSetChanged();

    }

    @Override
    public void onAddClick(boolean value)
    {
        if(value==true)
        {
            count=count+1;
            toolbarTextView_count.setText(Integer.toString(count));
        }
        else{
            count=count-1;
            toolbarTextView_count.setText(Integer.toString(count));
        }

    }

    @OnClick(R.id.next)
    public void OnNextClick()
    {
       startActivity(new Intent(HomeActivity.this,ImageAssignmentActivity.class));
    }
}
