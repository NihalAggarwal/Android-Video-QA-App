package com.example.video_qa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myViewHolder> implements Filterable {

    ArrayList<Model> data;
    ArrayList<Model> backup;
    Context context;

    public myAdapter(ArrayList<Model> data, Context context) {
        this.data = data;
        this.context = context;

        backup = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        //final Model temp = data.get(position);
        holder.t1.setText(data.get(position).getHeader());
        holder.t2.setText(data.get(position).getDesc());
        holder.img.setImageResource(data.get(position).getImgName());

        System.out.println(data.get(position).getHeader());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println();

                if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("Java")){
                    Intent intent = new Intent(context, java.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("JavaScript")){
                    Intent intent = new Intent(context, JavaScript.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("C++")){
                    Intent intent = new Intent(context, c_plus_plus.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("Python")){
                    Intent intent = new Intent(context, python.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("Competitive Programing")){
                    Intent intent = new Intent(context, cp.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("Data Science")){
                    Intent intent = new Intent(context, data_science.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("Blockchain")){
                    Intent intent = new Intent(context, blockchain.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("DevOps")){
                    Intent intent = new Intent(context, devops.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("Web Dev")){
                    Intent intent = new Intent(context, web_dev.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("App Dev")){
                    Intent intent = new Intent(context, app_dev.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(data.get(holder.getBindingAdapterPosition()).getHeader().equals("Big Data")){
                    Intent intent = new Intent(context, big_data.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<Model> filtered_data = new ArrayList<>();

            if (keyword.toString().isEmpty()) {
                filtered_data.addAll(backup);
            } else {
                for (Model obj : backup) {
                    if (obj.getHeader().toString().toLowerCase().contains(keyword.toString().toLowerCase())) {
                        filtered_data.add(obj);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered_data;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            data.clear();
            data.addAll((ArrayList<Model>) results.values);
            notifyDataSetChanged();
        }
    };
}
