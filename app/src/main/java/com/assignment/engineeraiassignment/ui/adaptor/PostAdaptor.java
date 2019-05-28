package com.assignment.engineeraiassignment.ui.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.engineeraiassignment.R;
import com.assignment.engineeraiassignment.model.Post;
import com.assignment.engineeraiassignment.ui.Callback;

import java.util.Date;
import java.util.List;

public class PostAdaptor extends RecyclerView.Adapter<PostAdaptor.MyViewHolder> {
    private Callback listener;
    public void setListener(Callback listener)    {
        this.listener = listener;
    }

    private List<Post> postList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,createdAt;
        ToggleButton toggleButton;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.id_post_title);
            createdAt = (TextView) view.findViewById(R.id.id_post_created_at);
            toggleButton = (ToggleButton) view.findViewById(R.id.post_toggle_enable);
        }
    }




    public PostAdaptor(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post, parent, false);
        MyViewHolder myViewHolder =new MyViewHolder(itemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdaptor.MyViewHolder holder, int position)
    {
        Post post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.createdAt.setText(post.getCreated_at());
        holder.toggleButton.setChecked(post.isEnable());
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean value) {
                if(listener!=null)
                {
                    listener.onAddClick(value);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }






}
