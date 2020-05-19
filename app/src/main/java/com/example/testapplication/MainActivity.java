package com.example.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.chrono.MinguoChronology;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="---MainActivity的生命周期---";
    private NetworkChangeReceiver networkChangeReceiver;
    private IntentFilter intentFilter;
    private Button bt_1,bt_2,bt_3,bt_4,bt_5,bt_6,bt_7,bt_8,bt_9,bt_10,bt_11,bt_12,bt_13,bt_14,bt_15,bt_16,bt_17,bt_18;
    EditText username;
    EditText password;
    private TextView gender;
    private Dialog dialog;
    private ImageView imageView;
    private TextView toSD;
    private TextView toPaint;
    private TextView toGesture;
    private TextView toSharedPreferences;
    private TextView toFragment;
    private TextView toJSON;
    private TextView toProgress;
    private TextView toBroadcast;
    private TextView toPhone;
    private  TextView toDrawer;
    private Handler handler;
    private DrawerLayout myDrawLayout;
    private TextView normalNotify;
    private TextView largeNotify;
    private TextView floatNotify;
    private NotificationManager notificationManager;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        /*网络广播监听*/
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
        /*将自定义toolbar替换掉系统的actionBar*/
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        myDrawLayout = (DrawerLayout)findViewById(R.id.myDrawerLayout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        bt_1 = findViewById(R.id.bt_constraintLayout);
        bt_2 = findViewById(R.id.bt_frameLayout);
        bt_3 = findViewById(R.id.bt_tableLayout);
        bt_4 = findViewById(R.id.bt_linearLayout);
        bt_5 = findViewById(R.id.bt_differentButton);
        bt_6 = findViewById(R.id.bt_textView);
        bt_7 = findViewById(R.id.bt_toast);
        bt_8 = findViewById(R.id.bt_scrollView);
        bt_9 = findViewById(R.id.bt_simpleDialog);
        bt_10 = findViewById(R.id.bt_viewDialog);
        bt_11 = findViewById(R.id.toConfiguration);
        bt_12 = findViewById(R.id.register);
        bt_13 = findViewById(R.id.toRecyclerView);
        bt_14 = findViewById(R.id.animationButton);
        bt_15 = findViewById(R.id.toRelativeLayout);
        bt_16 = findViewById(R.id.toListView);
        bt_17 = findViewById(R.id.countries);
        bt_18 = findViewById(R.id.popup);
        gender = findViewById(R.id.getGender);
        handler=  new Handler(){
            public void handleMessage(Message message){
                switch (message.what){
                    case 1:
                        bt_7.setText(String.valueOf(message.what));
                        //Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_LONG);
                }
            }
        };
        toSharedPreferences = findViewById(R.id.toSharedPreferences);
        toSharedPreferences.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SharedPreferencesActivity.class);
            startActivity(intent);
        });
        toSD = findViewById(R.id.toSD);
        toSD.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this,SDControlActivity.class);
            startActivity(intent);
        });
        toPaint = findViewById(R.id.toPaint);
        toPaint.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PaintPathActivity.class);
            startActivity(intent);
        });
        toGesture = findViewById(R.id.toGesture);
        toGesture.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,GestureActivity.class);
            startActivity(intent);
        });
        toFragment = findViewById(R.id.toFragment);
        toFragment.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,FragmentActivity.class);
            startActivity(intent);
        });
        toJSON = findViewById(R.id.toJSON);
        toJSON.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,JsonActivity.class);
            startActivity(intent);
        });
        toProgress = findViewById(R.id.toProgressBar);
        toProgress.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,ProgressBarActivity.class);
            startActivity(intent);
        });
        toBroadcast = findViewById(R.id.broadcast);
        toBroadcast.setOnClickListener(view -> {

        });
        toPhone=findViewById(R.id.callPhone);
        toPhone.setOnClickListener(view -> {
            /*判断用户是否已经授权了权限，如果是，调用call函数拉起拨话，如果不是，发起权限请求*/
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
            }else{
                call();
            }
        });
        toDrawer = findViewById(R.id.toDrawerLayout);
        toDrawer.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,DrawActivity.class);
            startActivity(intent);
        });
        normalNotify = findViewById(R.id.normalNotification);
        normalNotify.setOnClickListener(view -> {

            NotificationManagerCompat compat = NotificationManagerCompat.from(this);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com"));
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
            NotificationChannel channel = new NotificationChannel(getApplicationContext().getPackageName(),
                    "会话类型",
                    NotificationManager.IMPORTANCE_DEFAULT);
            builder.setSmallIcon(R.drawable.collect)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.collectone))
                    .setContentTitle("一二三")
                    .setContentText("四五六")
                    .setContentIntent(pendingIntent)
                    .setChannelId(getApplicationContext().getPackageName());
            compat.createNotificationChannel(channel);
            compat.notify(122,builder.build());

            if(!compat.areNotificationsEnabled()){
                Toast.makeText(MainActivity.this, "没有通知权限", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "拥有通知权限", Toast.LENGTH_SHORT).show();
            }

        });
        floatNotify = findViewById(R.id.floatNotification);
        floatNotify.setOnClickListener(view -> {
            //builder与manager实例
            NotificationManagerCompat compat2 = NotificationManagerCompat.from(this);
            NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this);
            //Android8.0 新特性
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                // 通知渠道的id
                String id = "my_channel_01";
                // 用户可以看到的通知渠道的名字.
                CharSequence name = getString(R.string.channel_name);
                // 用户可以看到的通知渠道的描述
                String description = getString(R.string.channel_description);
                int importance = NotificationManager.IMPORTANCE_HIGH;
                //注意Name和description不能为null或者""
                NotificationChannel mChannel = new NotificationChannel(id, name, importance);
                // 配置通知渠道的属性
                mChannel.setDescription(description);
                //最后在notificationmanager中创建该通知渠道
                compat2.createNotificationChannel(mChannel);
                builder2.setChannelId(id);
                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            }else {
                layoutParams.type = WindowManager.LayoutParams.TYPE_PRIVATE_PRESENTATION;
            }


            /*一组intent+pendingIntent*/
            Intent myIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com"));
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,myIntent,0);
            /*另一组intent+pendingIntent*/
            Intent hangIntent = new Intent();
            hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            hangIntent.setClass(this,DrawActivity.class);
            PendingIntent hangPendingIntent = PendingIntent.getActivity(this,0,hangIntent,PendingIntent.FLAG_CANCEL_CURRENT);
            //配置builder的其他属性
            builder2.setSmallIcon(R.drawable.collect)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.collectone))
                    .setContentTitle("这是一个悬浮的通知栏")
                    .setContentText("悬浮的通知栏")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setTicker("新消息");
                    //.setFullScreenIntent(pendingIntent,false);
            compat2.notify(1,builder2.build());
        });
        Button [] buttons= {bt_1,bt_2,bt_3,bt_4,bt_5,bt_6,bt_7,bt_8,bt_9,bt_10,bt_11,bt_12,bt_13,bt_14,bt_15,bt_16,bt_17,bt_18};
        for(Button bt:buttons){
            bt.setOnClickListener(new MyClick());
        }
        gender.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this,ButtonActivity.class);
            startActivityForResult(intent,0);
        });
    }
    class MyClick implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.bt_constraintLayout:
                    setContentView(R.layout.constraintlayout);
                    break;
                case R.id.bt_frameLayout:
                    setContentView(R.layout.framelayout);
                    break;
                case R.id.bt_tableLayout:
                    setContentView(R.layout.tablelayout);
                    break;
                case R.id.bt_linearLayout:
                    setContentView(R.layout.linearlayout);
                    break;
                case R.id.bt_differentButton:
                    Intent intent = new Intent(MainActivity.this,ButtonActivity.class);
                    startActivity(intent);
                    break;
                case R.id.bt_textView:
                    setContentView(R.layout.textview);
                    break;
                case R.id.bt_toast:
                    //主线 show
                    //Toast.makeText(MainActivity.this,"这是一个toast",Toast.LENGTH_LONG).show();
                    new Thread(new Runnable(){
                        @Override
                        public void run(){
                            Message message = new Message();
                            message.what=1;
                            handler.sendMessage(message);
                        }
                    }).start();
                    break;
                case R.id.bt_scrollView:
                    setContentView(R.layout.scrollview);
                    break;
                case R.id.bt_simpleDialog:
                    simpleDialog();
                    break;
                case R.id.bt_viewDialog:
                    viewDialog();
                    break;
                case R.id.toConfiguration:
                    Intent intent1 = new Intent(MainActivity.this,ConfigurationActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.register:
                    //模拟注册传入数据操作
                    username = findViewById(R.id.username);
                    password = findViewById(R.id.password);
                    /*向intent中写入bundle传递数据*/
                    Bundle bundle = new Bundle();
                    bundle.putString("register","username:"+username.getText().toString()+";password:"+password.getText().toString());
                    Intent intent2 = new Intent(MainActivity.this,ButtonActivity.class);
                    intent2.putExtras(bundle);
                    startActivity(intent2);
                    break;
                case R.id.toRecyclerView:
                    Intent intent3 = new Intent(MainActivity.this, RecyclerViewActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.animationButton:
                    imageView = findViewById(R.id.animationImage);
                    Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_anim);
                    animation.setFillAfter(true);
                    imageView.setAnimation(animation);
                    imageView.startAnimation(animation);
                    Toast toast1 = Toast.makeText(MainActivity.this,"动画启动",Toast.LENGTH_LONG);
                    toast1.show();
                    break;
                case R.id.toRelativeLayout:
                    Intent intent4 = new Intent (MainActivity.this,RelativeLayoutActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.toListView:
                    Intent intent5 = new Intent(MainActivity.this,ListViewActivity.class);
                    startActivity(intent5);
                    break;
                case R.id.countries:
                    setContentView(R.layout.countriesdemo);
                    break;
                case R.id.popup:
                    initPopupView(v);
                    break;
                    default:

            }
        }
    }
    /*创建简单对话框 AlertDialog*/
    public void simpleDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("简单的对话框")
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("简单对话框的测试\n,第二行内容")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(MainActivity.this,"按下了确定按钮",Toast.LENGTH_LONG);
                        toast.show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast =Toast.makeText(MainActivity.this,"按下了取消按钮",Toast.LENGTH_LONG);
                        toast.show();
                    }
                })
                .setNeutralButton("中间按钮",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i){
                    }
                }).create().show();
    }
    /*把自定义View嵌入到dialog的message中当作内容*/
    public void viewDialog(){
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.tablelayout,null);
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher_background)
                .setTitle("自定义View对话框")
                .setView(linearLayout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(MainActivity.this,"按下了确定按钮",Toast.LENGTH_LONG);
                        toast.show();
                    }
                }).create().show();
    }
    /*重写方法获得ButtonActivity的返回结果*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0&&requestCode==0){
            Bundle bundle = data.getExtras();
            String genderResult = bundle.getString("gender");
            gender.setText(genderResult);
        }
    }

    public void ac1OnClick(View view){
        Intent intent = new Intent();
        intent.setData(Uri.parse("lee://www.crazyit.org:1234/test"));
        startActivity(intent);
    }
    public void ac2OnClick(View view){
        Intent intent = new Intent();
        intent.setData(Uri.parse("lee://www.fkjava.org:8888/test"));
        startActivity(intent);
    }
    public void ac3OnClick(View view){
        Intent intent = new Intent();
        intent.setData(Uri.parse("lee://www.fkjava.org:1234/mypath"));
        startActivity(intent);
    }
    public void ac4OnClick(View view){
        Intent intent = new Intent();
        intent.setData(Uri.parse("lee://www.fkjava.org:8888/mypath"));
        startActivity(intent);
    }
    public void ac5OnClick(View view){
        Intent intent = new Intent();
        intent.setData(Uri.parse("lee://www.fkjava.org:8888/mypath"));
        startActivity(intent);
    }

    private void initPopupView(View v){
        View popupWindow_view = getLayoutInflater().from(MainActivity.this).inflate(R.layout.popup,null,false);
        popupWindow = new PopupWindow(popupWindow_view,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        TextView tx1 = popupWindow_view.findViewById(R.id.popName);

        //popupWindow.setBackgroundDrawable();
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        //showAsDropDown指定某控件，popUpWindow将显示在该控件的下方
        popupWindow.showAsDropDown(bt_15);
        //showAtLocation直接指定popUpWindow的显示位置
        //popupWindow.showAtLocation(v, Gravity.LEFT,0,0);

        //popupWindow.getBackground().setAlpha(100);//该方法设置透明度提示空指针异常
        /*
        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了提示栏",Toast.LENGTH_SHORT).show();
            }
        });
         */

        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                popupWindow.dismiss();
                Intent intent = new Intent(MainActivity.this,PaintPathActivity.class);
                startActivity(intent);

                return true;
            }
        });
        FloatingActionButton button = popupWindow_view.findViewById(R.id.closePop);
        button.setOnClickListener(view -> {
            popupWindow.dismiss();
            Toast.makeText(MainActivity.this,"你已经点击了悬浮按钮",Toast.LENGTH_SHORT).show();
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
        unregisterReceiver(networkChangeReceiver);
        Log.d(TAG,"-----onDestroy-----");
    }

    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context,Intent intent){
            ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context,"network is not available",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void call(){
        try{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    call();
                }else {
                    Toast.makeText(this,"you denied the permission",Toast.LENGTH_LONG).show();
                }
                break;
                default:
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                myDrawLayout.openDrawer(GravityCompat.START);
                break;
                default:
        }
        return true;
    }
}
