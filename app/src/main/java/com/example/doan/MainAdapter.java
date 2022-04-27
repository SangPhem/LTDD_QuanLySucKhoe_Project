package com.example.doan;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;
    Context ctx;


    public MainAdapter(Context ctx, List<String> titles, List<Integer> images){
        this.ctx = ctx;
        this.titles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_dashboard_recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.icon.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            icon = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Chọn Mục ngoài trang chủ
                    if(getAdapterPosition() == 0) {
                        ctx.startActivity(new Intent(ctx, WaterTrackerActivity.class));
                    } else if(getAdapterPosition() == 1){
                        ctx.startActivity(new Intent(ctx, StepCounterActivity.class));
                    } else if(getAdapterPosition() == 2){
                        ctx.startActivity(new Intent(ctx, HeartRateActivity .class));
                    } else if(getAdapterPosition() == 3) {
                        ctx.startActivity(new Intent(ctx, BMITrackerActivity.class));
                    }
                }
            });

        }
    }

}