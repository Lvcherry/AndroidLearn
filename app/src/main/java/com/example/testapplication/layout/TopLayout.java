package com.example.testapplication.layout;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapplication.R;

public class TopLayout extends LinearLayout {
    public TopLayout(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        LayoutInflater.from(context).inflate(R.layout.toptitle,this);
        TextView textView = findViewById(R.id.edit);
        TextView textView1 = findViewById(R.id.back);
        textView.setOnClickListener(view -> {
            Toast.makeText(getContext(),"click edit",Toast.LENGTH_LONG).show();
        });
        textView1.setOnClickListener(view -> {
            ((Activity)getContext()).finish();
        });
    }
}
