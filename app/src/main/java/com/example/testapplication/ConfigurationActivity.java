package com.example.testapplication;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationActivity extends AppCompatActivity {
    TextView textView1 ;
    TextView textView2 ;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configurationtest);
        textView1 = findViewById(R.id.changeConfiguration);
        textView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Configuration configuration = getResources().getConfiguration();
                if(configuration.orientation==Configuration.ORIENTATION_LANDSCAPE){
                    ConfigurationActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                if(configuration.orientation==Configuration.ORIENTATION_PORTRAIT){
                    ConfigurationActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        textView2 = findViewById(R.id.getOrientation);
        String s = newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE?"横向屏幕":"竖向屏幕";
        textView2.setText("屏幕方向发生改变，当前屏幕方向为："+s);
    }
}
