package com.example.myexpenditure;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        List<MainData> modelList;
        LayoutInflater layoutInflater;
        private Context context;
        private String from;


    public MyAdapter(Context context , List<MainData> modelList, String from){
        this.modelList = modelList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.from = from;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_details_light, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(from!= null && !from.isEmpty()) {
            if(from.matches("tagdetails")) {
                holder.layout_date.setVisibility(View.VISIBLE);
                holder.date.setText(modelList.get(position).getDate());
            }
            else if(from.matches("main"))
                holder.layout_date.setVisibility(View.GONE);
        }
        else{
            holder.layout_date.setVisibility(View.GONE);
        }

        holder.amount.setText("Rs. "+modelList.get(position).getAmount());
        holder.reason.setText(modelList.get(position).getReason());


        if(modelList.get(position).getTag().matches("Food")) {
            holder.imageView.setImageResource(R.drawable.ic_food);
        }
        else if(modelList.get(position).getTag().matches("Travel")) {
            holder.imageView.setImageResource(R.drawable.ic_travel);
        }

        else if(modelList.get(position).getTag().matches("Home")) {
            holder.imageView.setImageResource(R.drawable.ic_home);
        }

        else {
            holder.imageView.setImageResource(R.drawable.ic_others);
        }

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView amount, reason, date;
        ImageView imageView;
        LinearLayout layout_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            amount = itemView.findViewById(R.id.amount);
            reason = itemView.findViewById(R.id.reason);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.tagimage);

            layout_date = itemView.findViewById(R.id.layout_date);

        }
    }

}
