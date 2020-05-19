package com.example.testapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapplication.Adapter.ListViewAdapter;
import com.example.testapplication.entity.Story;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    /*简单化list view的数据初始化*/
    private String[] data={"尚米网络技术有限公司","Dell戴尔","地址解析与反向地址解析","获取定位信息","系统功能简介"
                            ,"使用json创建对象","用户登陆接口","查看物流客户端","视频上传bug"};
    ArrayList<Story> stories = new ArrayList<>();
    // private Story[] stories = null;
    @Override
    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.listview);

        /*简单化ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

         */
        /*自定义Adapter与数据*/
        initData();
        ListViewAdapter listViewAdapter  = new ListViewAdapter(ListViewActivity.this,R.layout.listviewitem,stories);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Story story = stories.get(position);
                Toast.makeText(ListViewActivity.this,"你点击了第"+(position+1)+"项，名字为"+story.getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initData(){
        String[] name=new String []{"虎头","武松","音乐","李白"};
        String[] desc=new String[]{"虎虎的头","打虎的人","知识源泉","诗仙啊诗仙"};
        //int[] id = new int[]{1113,4363,23363,2355};
        String[] id = new String[]{"t32cdg","36kvd9","3gk36","36dkkg"};
        for(int i= 0;i<name.length;i++){
            stories.add(new Story(name[i],desc[i],id[i]));
        }
    }

}

