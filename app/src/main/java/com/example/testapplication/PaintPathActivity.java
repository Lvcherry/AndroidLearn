package com.example.testapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PaintPathActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savaInstanceState){
        super.onCreate(savaInstanceState);
        /*直接在PaintPathActivity窗口新建一个继承后的MyView,而不选择layout文件*/
        setContentView(new MyView(this));
}
    /*继承View,并重写onDraw方法画出Path*/
    class MyView extends View {
        private float phase;
        private PathEffect[] effects = new PathEffect[7];
        private int[] colors;
        private Paint paint = new Paint();
        private Path path = new Path();
        public  MyView(Context context){
            super(context);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4);
            path.moveTo(0f,0f);
            for(int i= 0;i<40;i++){
                path.lineTo(i*25f,(float)(Math.random()*90));
            }
            colors = new int[]{Color.BLACK,Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.RED,Color.YELLOW};
            /*三条静态Path
            * effects[0]:PathEffect()每个点依次直线连接
            * effects[1]:CornerPathEffect(float radius)加入拐角时的弯曲度
            * effects[2]:DiscretePathEffect(float segmentLength,float deviation)
            *            打散Path的线段，使得在原来的基础上发生打散效果，segmentLength指定最大的段长，deviation指定绘制的偏移量*/
            effects[0] = null;
            effects[1] = new CornerPathEffect(10f);
            effects[2] = new DiscretePathEffect(3.0f,5.0f);
        }
        @Override
        public void onDraw(Canvas canvas){
            //背景填充白色
            canvas.drawColor(Color.WHITE);
            //将画布移动到（8，8），然后开始绘制
            canvas.translate(8f,8f);
            //七种样式、七种颜色、绘制七条path
            for(int i=0;i<7;i++){
                paint.setPathEffect(effects[i]);
                paint.setColor(colors[i]);
                //drawPath绘制path
                canvas.drawPath(path,paint);
                //移动canvas
                canvas.translate(0f,90f);
            }
            /*四条动态曲线
            * effect[3]:DashPathEffect(float[] intervals，float phase)
            *           将path线段虚线化，intervals为虚线的on/off数组，phase为绘制时的偏移量
            * effect[4]:PathDashPathEffect(Path shape, float advance, float phase, PathDashPathEffect.Style style)
            *           shape:填充图形，advance：每个填充图形之间的间隔，phase：偏离量，style:该类自由的枚举值
            * effect[5]:ComposePathEffect(PathEffect 1,PathEffect 2)
            *           两个PathEffect的组合效果,先将2绘制出来，然后在2的基础上增加1的效果
            * effect[6]:SumPathEffect(PathEffect first, PathEffect second)
            *           两个PathEffect的叠加效果，将两 个参数的效果展现出来，然后简单叠加*/
            effects[3] = new DashPathEffect(new float[]{20f,10f,5f,10f},phase);
            Path p = new Path();
            p.addRect(0f,0f,8f,8f,Path.Direction.CCW);
            effects[4] = new PathDashPathEffect(p,12f,phase,PathDashPathEffect.Style.ROTATE);
            effects[5] = new ComposePathEffect(effects[2],effects[4]);
            effects[6] = new SumPathEffect(effects[4],effects[3]);
            //改变phase值
            phase+=1f;
            //调用invalidate（）函数，请求重新draw()，重新绘制MyView,达到动画效果
            invalidate();
        }
    }
}
