package com.assignment.engineeraiassignment.ui.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.engineeraiassignment.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class UserItemAdaptor extends RecyclerView.Adapter<UserItemAdaptor.MyViewHolder> {

    List<String> items;
    Context mContext;

    public UserItemAdaptor(List<String> items,Context context) {
        this.items = items;
        this.mContext=context;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView_items;

        public MyViewHolder(View view) {
            super(view);
            imageView_items = (ImageView) view.findViewById(R.id.image_grid_item);


        }
    }
    @NonNull
    @Override
    public UserItemAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_row, parent, false);
        UserItemAdaptor.MyViewHolder myViewHolder =new UserItemAdaptor.MyViewHolder(itemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemAdaptor.MyViewHolder holder, int position)
    {
        if(items.size()==3)
        {
            if(position==2)
            {
                holder.imageView_items.setScaleType(ImageView.ScaleType.FIT_END);
            }
        }
       String itemImage=items.get(position);
       String[] itemImageSplit=itemImage.split(":");
        itemImage="https:"+itemImageSplit[1];
        Glide.with(mContext)
                .load(itemImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView_items);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
