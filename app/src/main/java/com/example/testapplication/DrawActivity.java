package com.example.testapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.testapplication.Adapter.LessonRecyclerViewAdapter;
import com.example.testapplication.entity.Lesson;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawActivity extends AppCompatActivity {
    private FloatingActionButton button;
    private DrawerLayout myDrawerLayout;
    private SwipeRefreshLayout refreshLayout;
    private LessonRecyclerViewAdapter adapter;
    private NotificationManager notificationManager ;
    private Lesson[] lessons = {new Lesson("语文",R.drawable.chinese1),new Lesson("数学",R.drawable.math1),new Lesson("英语",R.drawable.english1),
                                new Lesson("生物",R.drawable.biology1),new Lesson("化学",R.drawable.chemistry1),new Lesson("物理",R.drawable.physics1),
                                new Lesson("音乐",R.drawable.music1),new Lesson("美术",R.drawable.paint1),new Lesson("体育",R.drawable.pe1),
                                new Lesson("科学",R.drawable.science1)};

    private List<Lesson> lessonList = new ArrayList<Lesson>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);
        myDrawerLayout = findViewById(R.id.drawerLayout);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /*把ActionBar替换为自定义的toolbar,并添加菜单按钮*/
        Toolbar toolbar = findViewById(R.id.drawToolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);

        }
        /*悬浮按钮的点击事件+snackBar*/
        button = findViewById(R.id.fab);
        button.setOnClickListener(view -> {
            Snackbar.make(view,"data deleted",Snackbar.LENGTH_SHORT)
                    .setAction("undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(DrawActivity.this,"data restored",Toast.LENGTH_LONG).show();
                        }
                    }).show();
        });
        /*初始化数据*/
        initData();
        /*
        recyclerView                    获得recyclerView控件ID
        + manager                       在当前上下文，创建一个layoutManager
        + adapter                       把初始化好的list数据，传入自定义的adapter中
        + recyclerView.setLayoutManger  recyclerView设置布局管理
        + recyclerView.setAdapter       recyclerView设置适配器
        */
        RecyclerView recyclerView = findViewById(R.id.drawerRecyclerView);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        adapter = new LessonRecyclerViewAdapter(lessonList);
        /*通过adapter中listener接口定义+onBindViewHolder中绑定具体view的点击事件+Java代码的adapter.setOnItemClickListener注册点击逻辑
        adapter.setOnItemClickListener(new LessonRecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(DrawActivity.this,"点击了第"+(position+1)+"条，itemId为"
                ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });*/
        recyclerView.setAdapter(adapter);

        /*下拉刷新的实现*/
        refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLessons();
            }
        });

        /*对navigationCView的item设置监听*/
        NavigationView navigationView = findViewById(R.id.navigationView);
        /*把navigationView默认的itemIconTint设为null,这样才会应用自己自定义的icon样式，
        *  否则icon只会显示一列灰色方块，在xml文件navigationView标签中用app:itemIconTint设置这一系列方块的颜色
        * */
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.my_collection:
                        Toast.makeText(DrawActivity.this,"这是收藏",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.my_asset:
                        Toast.makeText(DrawActivity.this,"这是资产",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.normalNotify:
                        NotificationChannel channel = new NotificationChannel(getApplicationContext().getPackageName(),
                                "会话类型",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManagerCompat compat = NotificationManagerCompat.from(DrawActivity.this);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(DrawActivity.this)
                                .setSmallIcon(R.drawable.collect)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.collectone))
                                .setContentTitle("一二三")
                                .setContentText("四五六")
                                .setChannelId(getApplicationContext().getPackageName());
                        compat.createNotificationChannel(channel);
                        compat.notify(122,builder.build());
                        break;
                    case R.id.largeNotification:
                        break;
                    case R.id.floatNotification:
                        Intent myIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com"));
                        PendingIntent pendingIntent = PendingIntent.getActivity(DrawActivity.this,0,myIntent,0);
                        Intent hangIntent = new Intent();
                        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        hangIntent.setClass(DrawActivity.this,MainActivity.class);
                        PendingIntent hangPendingIntent = PendingIntent.getActivity(DrawActivity.this,0,hangIntent,PendingIntent.FLAG_CANCEL_CURRENT);
                        NotificationChannel channel2 = new NotificationChannel(getApplicationContext().getPackageName(),
                                "悬浮的通知栏",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManagerCompat compat2 = NotificationManagerCompat.from(DrawActivity.this);
                        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(DrawActivity.this)
                                .setSmallIcon(R.drawable.collect)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.collectone))
                                .setContentTitle("这是一个悬浮的通知栏")
                                .setContentText("悬浮的通知栏")
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true)
                                .setChannelId(getApplicationContext().getPackageName())
                                .setFullScreenIntent(hangPendingIntent,true);
                        compat2.createNotificationChannel(channel2);
                        compat2.notify(122,builder2.build());
                        break;
                        default:
                }
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                myDrawerLayout.openDrawer(GravityCompat.START);
                break;
                default:

        }
        return true;
    }

    private void initData(){
        lessonList.clear();
        for(int i=0;i<50;i++){
            Random random = new Random();
            int index = random.nextInt(lessons.length);
            lessonList.add(lessons[index]);
        }
    }

    private void refreshLessons(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
