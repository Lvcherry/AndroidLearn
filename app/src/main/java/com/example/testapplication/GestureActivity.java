package com.example.testapplication;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GestureActivity extends AppCompatActivity {
    /*实现效果：view识别手势位置，并移动图片到手势位置*/
    private GestureDetector gestureDetector;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.gesturelayout);
        imageView = findViewById(R.id.dragImage);
        int x= (int)imageView.getX();
        int y= (int)imageView.getY();
        imageView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        gestureDetector.onTouchEvent(motionEvent);
                        return true;
                    }
                }
        );


        gestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1,MotionEvent e2,float distanceX,float distanceY){
                imageView.layout((int)(imageView.getLeft()+distanceX),(int)(imageView.getTop()+distanceY),
                        (int)(imageView.getRight()+distanceX),(int)(imageView.getBottom()+distanceY));
                return true;
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent e){
        return gestureDetector.onTouchEvent(e);
    }
}
