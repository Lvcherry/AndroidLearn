package com.example.testapplication;

import android.app.Person;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.entity.Story;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Story> storyList = new ArrayList<Story>();
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.recyclerview);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //recyclerView四大部件之一：layoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //初始化item数据
        initData();
        //recyclerView四大部件之一：adapter
        RecyclerView.Adapter adapter = new RecyclerView.Adapter<StoryViewHolder>() {
            @Override
            public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.recyclerviewitem,null);
                return new StoryViewHolder(view,this);
            }
            @Override
            public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
                holder.storyName.setText(storyList.get(position).getName());
                holder.storyDesc.setText(storyList.get(position).getDesc());
                holder.storyID.setText(storyList.get(position).getId());
                //holder.storyID.setText(storyList.get(position).getId()+"");
            }
            @Override
            public int getItemCount() {
                return storyList.size();
            }
        };
        recyclerView.setAdapter(adapter);
    }
    //初始化数据方法
    private void initData(){
        String[] name=new String []{"虎头","武松","音乐","李白"};
        String[] desc=new String[]{"虎虎的头","打虎的人","知识源泉","诗仙啊诗仙"};
        //int[] id = new int[]{1113,4363,23363,2355};
        String[] id = new String[]{"t32cdg","36kvd9","3gk36","36dkkg"};
        for(int i= 0;i<name.length;i++){
            storyList.add(new Story(name[i],desc[i],id[i]));
        }
    }
    class StoryViewHolder extends RecyclerView.ViewHolder{
        TextView storyName;
        TextView storyDesc;
        TextView storyID;
        private RecyclerView.Adapter adapter;
        public StoryViewHolder(View itemView, RecyclerView.Adapter adapter){
            super(itemView);
            this.storyDesc = itemView.findViewById(R.id.storyDesc);
            this.storyName = itemView.findViewById(R.id.storyName);
            this.storyID = itemView.findViewById(R.id.storyID);
            this.adapter = adapter;
        }
    }

}
