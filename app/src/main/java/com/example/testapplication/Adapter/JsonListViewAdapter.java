package com.example.testapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.testapplication.R;
import com.example.testapplication.entity.JsonData;
import com.example.testapplication.entity.Story;

import java.util.HashMap;
import java.util.List;

public class JsonListViewAdapter extends ArrayAdapter {
    private final int resourceId;
    public JsonListViewAdapter(Context context, int resource, List<JsonData> object){
        super(context,resource,object);
        resourceId = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        JsonData data = (JsonData)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView key = view.findViewById(R.id.tv_json_key);
        TextView value = view.findViewById(R.id.tv_json_value);
        key.setText(data.getKey());
        value.setText(data.getValue());
        return view;
    }
}
