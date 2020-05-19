package com.example.testapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;
import com.example.testapplication.entity.JsonData;

import java.util.List;

public class JsonRecyclerViewAdapter extends RecyclerView.Adapter<JsonRecyclerViewAdapter.ViewHolder> {

    private List<JsonData> list;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView data_key;
        TextView data_value;
        public ViewHolder(View view){
            super(view);
            data_key = view.findViewById(R.id.tv_json_key);
            data_value = view.findViewById(R.id.tv_json_value);
        }
    }

    public JsonRecyclerViewAdapter(List<JsonData> dataList){
        list = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jsonlistviewitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JsonData data = list.get(position);
        holder.data_key.setText(data.getKey());
        holder.data_value.setText(data.getValue());
    }
    @Override
    public int getItemCount(){
        return list.size();
    }

}
