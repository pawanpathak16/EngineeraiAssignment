package com.assignment.engineeraiassignment.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.engineeraiassignment.AppConstants;
import com.assignment.engineeraiassignment.R;
import com.assignment.engineeraiassignment.data.remote.api.PostsApiResponse;
import com.assignment.engineeraiassignment.data.remote.api.UsersApiResponse;
import com.assignment.engineeraiassignment.factory.ViewModelFactory;
import com.assignment.engineeraiassignment.model.User;
import com.assignment.engineeraiassignment.model.UserApiResult;
import com.assignment.engineeraiassignment.ui.adaptor.PostAdaptor;
import com.assignment.engineeraiassignment.ui.adaptor.UsersAdaptor;
import com.assignment.engineeraiassignment.viewmodel.Hits;
import com.assignment.engineeraiassignment.viewmodel.PostsViewModel;
import com.assignment.engineeraiassignment.viewmodel.UsersViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

import static com.assignment.engineeraiassignment.data.remote.api.Status.ERROR;

public class ImageAssignmentActivity extends AppCompatActivity
{

    @Inject
    Gson gson;

    @Inject
    ViewModelFactory viewModelFactory;
    UsersViewModel viewModel;

    @BindView(R.id.recycler_view_users)
    RecyclerView mRecyclerView;

    UsersAdaptor mUserAdaptor;
    List<User> mUsersList=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_assignment);
        ButterKnife.bind(this);


        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mUserAdaptor =new UsersAdaptor(mUsersList,ImageAssignmentActivity.this);
        mRecyclerView.setAdapter(mUserAdaptor);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));



        // AppConstants.BASE_URL="http://sd2-hiring.herokuapp.com/api/";



        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel.class);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel.class);


        viewModel.usersResponse().observe(this, this::consumeResponse);

    }


    @Override
    protected void onResume() {

        super.onResume();
        viewModel.getUsers(10,10);
    }

    protected void consumeResponse(UsersApiResponse apiResponse)
    {
        switch (apiResponse.status) {

            case LOADING:
                // progressDialog.show();
                break;

            case SUCCESS:
                try {
                   // System.out.println("---->" + apiResponse.data.);
                    UserApiResult result =gson.fromJson(apiResponse.data,UserApiResult.class);
                    System.out.println(result.getData());
                    updateUi(result.getData().getUsers());

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                break;

            case ERROR:
                Log.e("error",apiResponse.error.getMessage());
                break;

            default:
                break;
        }

    }

    private void updateUi(List<User> users)
    {
        mUsersList.clear();
        for (User user : users) {
            mUsersList.add(user);
        }
        mUserAdaptor.notifyDataSetChanged();

    }
}
