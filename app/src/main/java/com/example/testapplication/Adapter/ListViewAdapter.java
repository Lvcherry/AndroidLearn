package com.example.testapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.testapplication.R;
import com.example.testapplication.entity.Story;

import org.w3c.dom.Text;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter {
    private final int resourceId;
    public ListViewAdapter(Context context, int resource, List<Story> object){
        super(context,resource,object);
        resourceId = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Story story = (Story) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView name = view.findViewById(R.id.ListViewName);
        TextView desc = view.findViewById(R.id.ListViewDesc);
        TextView id = view.findViewById(R.id.ListViewId);
        name.setText(story.getName());
        desc.setText(story.getDesc());
        id.setText(story.getId());
        return view;
    }
}
