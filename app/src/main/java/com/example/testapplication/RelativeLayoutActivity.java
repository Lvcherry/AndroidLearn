package com.example.testapplication;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class RelativeLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.relativelayout);
        ImageView imageView = (ImageView)findViewById(R.id.RelativeViewImage1);
        Button button1 = findViewById(R.id.changeRelativeView);
        Button button = findViewById(R.id.getRelativeImageSize);
        TextView textView = findViewById(R.id.showRelativeImageSize);
        button.setOnClickListener(view -> {
            ViewGroup.LayoutParams  layoutParams;
            layoutParams = imageView.getLayoutParams();
            imageView.setMaxHeight(240);
            imageView.setMaxWidth(240);
            //textView.setText("Height:"+height+";width:"+layoutParams.width);

        });
        button1.setOnClickListener(view -> {
            
            //imageView.setLayoutDirection();
        });
    }
}
