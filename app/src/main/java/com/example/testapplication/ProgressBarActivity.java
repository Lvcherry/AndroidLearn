package com.example.testapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ProgressBarActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar bar1;
    private ProgressBar bar2;
    private ProgressBar bar3;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.progressbar);
        TextView bt_1 = findViewById(R.id.hideProgress);
        TextView bt_2 = findViewById(R.id.addProgress);
        TextView bt_3 = findViewById(R.id.autoAddProgress);
        TextView bt_4 = findViewById(R.id.progressDialog);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.hideProgress:
                bar1 = findViewById(R.id.Bar1);
                if(bar1.getVisibility()==View.VISIBLE){
                    bar1.setVisibility(View.GONE);
                }else{
                    bar1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.addProgress:
                bar1 = findViewById(R.id.Bar2);
                bar1.setProgress(bar1.getProgress()+10);
                break;
            case R.id.autoAddProgress:
                bar1 = findViewById(R.id.Bar3);
                if(bar1.getProgress()<99) {
                    Timer timer = new Timer();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            bar1.setProgress(bar1.getProgress()+1);
                        }
                    }, 300, 300);
                }
                break;
            case R.id.progressDialog:
                ProgressDialog progressDialog = new ProgressDialog(ProgressBarActivity.this);
                progressDialog.setTitle("this is a progressDialog");
                progressDialog.setMessage("Loading");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
                default:
                    break;
        }
    }
}
