package com.example.testapplication.entity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class DragView extends View {
    private int lastX;
    private int lastY;
    private GestureDetector detector;
    public DragView(Context context, AttributeSet set){
        super(context,set);
    }
    public boolean onTouchEvent(MotionEvent e){

        /*对dragView进行模式设定*/
       int x =(int)e.getX();
        int y =(int)e.getY();
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
            case MotionEvent.ACTION_MOVE:
                int offX = x-lastX;
                int offY = y-lastY;
                layout(getLeft()+offX,getTop()+offY,
                        getRight()+offX,getBottom()+offY);
                break;
        }
        return true;

    }


}
