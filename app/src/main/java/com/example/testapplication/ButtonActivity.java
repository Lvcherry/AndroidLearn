package com.example.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.differbutton);
        Button getResultButton = findViewById(R.id.getRegisterResult);
        RadioGroup radioGroup = findViewById(R.id.genderGroup);
        //将从MainActivity中获得的数据以toast显示
        getResultButton.setOnClickListener(view -> {
            Intent intent = getIntent();
            Toast toast = Toast.makeText(ButtonActivity.this,intent.getExtras().getString("register"),Toast.LENGTH_LONG);
            toast.show();
        });
        //获取radioGroup的选中值并返回MainActivity
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radio, int i) {
                RadioButton radioButton = (RadioButton)ButtonActivity.this.findViewById(radio.getCheckedRadioButtonId());
                Bundle bundle = new Bundle();
                bundle.putString("gender",radioButton.getText().toString());
                Intent intent = getIntent();
                intent.putExtras(bundle);
                ButtonActivity.this.setResult(0,intent);
                ButtonActivity.this.finish();
            }
        });

    }
}
