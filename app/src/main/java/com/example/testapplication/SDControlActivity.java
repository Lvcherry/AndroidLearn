package com.example.testapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class SDControlActivity extends AppCompatActivity {
    private static final String TAG="--SDActivity的生命周期--";
    private static final String FILE_NAME = "/crazyit.bin";
    private TextView outputData;
    private EditText inputData;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.sdcontroller);
        TextView input = findViewById(R.id.inputSD);
        TextView output = findViewById(R.id.outputSD);
        outputData = findViewById(R.id.outputSDData);
        inputData = findViewById(R.id.inputSDData);
        input.setOnClickListener(view -> {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0x123);
        });
        output.setOnClickListener(view -> {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0x456);
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"-----onStart-----");
    }
    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(TAG,"------onRestart-----");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"-----onResume-----");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"-----onPause-----");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"-----onStop-----");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"-----onDestroy-----");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        if(requestCode ==0x123){
            if(grantResults!=null&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                write(inputData.getText().toString());
                inputData.setText("");
            }else{
                Toast.makeText(SDControlActivity.this,"没有写入权限",Toast.LENGTH_LONG).show();
            }

        }
        if(requestCode==0x456){
            if(grantResults!=null&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                outputData.setText(read());
            }else{
                Toast.makeText(SDControlActivity.this,"没有读取权限",Toast.LENGTH_LONG).show();
            }
        }
    }

    private String read(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File sdCardDir = Environment.getExternalStorageDirectory();
            try(
                    FileInputStream fis = new FileInputStream(sdCardDir.getCanonicalPath()+FILE_NAME);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis))){
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while((line= bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    private void write(String string){
        File sdCardDir = Environment.getExternalStorageDirectory();
        try{
            File targetFile = new File(sdCardDir.getCanonicalPath()+FILE_NAME);
            RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile,"rw");
            randomAccessFile.seek(targetFile.length());
            randomAccessFile.write(string.getBytes());
            randomAccessFile.close();

        }catch (IOException E){
            E.printStackTrace();
        }
    }




}
