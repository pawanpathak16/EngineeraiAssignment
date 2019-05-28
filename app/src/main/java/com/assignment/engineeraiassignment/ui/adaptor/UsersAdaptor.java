package com.assignment.engineeraiassignment.ui.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.engineeraiassignment.R;
import com.assignment.engineeraiassignment.model.Post;
import com.assignment.engineeraiassignment.model.User;
import com.assignment.engineeraiassignment.ui.Callback;
import com.assignment.engineeraiassignment.ui.ImageAssignmentActivity;
import com.bumptech.glide.Glide;

import java.util.Date;
import java.util.List;

public class UsersAdaptor extends RecyclerView.Adapter<UsersAdaptor.MyViewHolder> {


    private List<User> usersList;
    private  Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public ImageView imageView_User;
        RecyclerView recyclerView_items;

        public MyViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.imageview_username);
            imageView_User = (ImageView) view.findViewById(R.id.imageview_usermage);
            recyclerView_items = (RecyclerView) view.findViewById(R.id.recycler_items);

        }
    }




    public UsersAdaptor(List<User> usersList, Context context) {
        this.usersList=usersList;
        this.mContext=context;
    }

    @NonNull
    @Override
    public UsersAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_row, parent, false);
        UsersAdaptor.MyViewHolder myViewHolder =new UsersAdaptor.MyViewHolder(itemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        GridLayoutManager gridLayoutManager=null;

        User user = usersList.get(position);
      //  Glide.with(mContext).load(user.getImage()).into(holder.imageView_User);
        String userImage=user.getImage();
        String[] userimageSubarray=userImage.split(":");
        userImage="https:"+userimageSubarray[1];
        Glide
                .with(mContext)
                .load(userImage)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView_User);
        holder.userName.setText(user.getName());
        UserItemAdaptor itemAdaptor=new UserItemAdaptor(user.getItems(),mContext);
        if(usersList.get(position).getItems().size()==1)
        {
            gridLayoutManager = new GridLayoutManager(mContext,1);
        }
        else{
            gridLayoutManager = new GridLayoutManager(mContext,2);
        }


        holder.recyclerView_items.setLayoutManager(gridLayoutManager);
        holder.recyclerView_items.setHasFixedSize(true);
       // LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        holder.recyclerView_items.setLayoutManager(gridLayoutManager);

        holder.recyclerView_items.setAdapter(itemAdaptor);
        itemAdaptor.notifyDataSetChanged();
        //holder.recyclerView_items.addItemDecoration(new DividerItemDecoration(holder.recyclerView_items.getContext(), DividerItemDecoration.VERTICAL));
       // holder.recyclerView_items.addItemDecoration(new DividerItemDecoration(holder.recyclerView_items.getContext(), DividerItemDecoration.HORIZONTAL));


    }



    @Override
    public int getItemCount() {
        return usersList.size();
    }

}
