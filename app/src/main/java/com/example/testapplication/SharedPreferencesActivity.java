package com.example.testapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferencesActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.sharedpreferences);
        EditText  editText = findViewById(R.id.inputData);
        TextView textViewSet = findViewById(R.id.input);
        TextView textViewGet = findViewById(R.id.getAllData);


        //获取sharedPreferences
        preferences = getSharedPreferences("crazyit", Context.MODE_PRIVATE);
        //获取sharedPreferences.editor
        editor = preferences.edit();
        textViewSet.setOnClickListener(view ->{
            if(editText.getText().toString().trim().equals("")||editText.getText().toString().trim().equals("输入要存入的数据")){
                Toast.makeText(SharedPreferencesActivity.this,"请输入要存入的数据",Toast.LENGTH_LONG).show();
            }else{
                editor.putString("data",editText.getText().toString());
                editor.apply();
                Toast.makeText(SharedPreferencesActivity.this,"存入成功",Toast.LENGTH_LONG).show();
            }
        });
        textViewGet.setOnClickListener(view -> {
            String data = preferences.getString("data",null);
            String result = data==null?"暂无数据":"存入的数据为："+data;
            Toast.makeText(SharedPreferencesActivity.this,result,Toast.LENGTH_LONG).show();
        });
    }
}
