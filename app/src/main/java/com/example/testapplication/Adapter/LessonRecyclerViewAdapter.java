package com.example.testapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testapplication.R;
import com.example.testapplication.entity.Lesson;

import java.util.List;

public class LessonRecyclerViewAdapter extends RecyclerView.Adapter<LessonRecyclerViewAdapter.ViewHolder> {
    private Context context;        //指定上下文
    private List<Lesson> list;      //指定数据
    private onItemClickListener listener  =null;
    /*前期准备：继承viewHolder 用于封装数据*/
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView lessonImage;
        public TextView lesosnName;
        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            lesosnName = view.findViewById(R.id.lesson_name);
            lessonImage = view.findViewById(R.id.lesson_image);
        }
    }

    //第一步：Adapter构造函数中把lesson list数据传入
    public LessonRecyclerViewAdapter(List<Lesson> lessonList){
        list = lessonList;
    }

    //前期准备：在重写的onCreateViewHolder中把每一项的布局传入
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int ViewType){
        if(context==null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.cardviewitem,parent,false);
        //新建viewHolder实例，对holder中的控件设置点击事件，
        // 将recyclerView的点击事件转化成了具体控件(imageView、textView)的点击事件
        final ViewHolder holder = new ViewHolder(view);
        holder.lessonImage.setOnClickListener(view1 -> {
            int position = holder.getLayoutPosition();
            Lesson lesson = list.get(position);
            Toast.makeText(view.getContext(),"你点击了图片"+lesson.getName(),Toast.LENGTH_SHORT).show();
        });
        holder.lesosnName.setOnClickListener(view1 -> {
            int position = holder.getLayoutPosition();
            Lesson lesson = list.get(position);
            Toast.makeText(view.getContext(),"你点击了标题"+lesson.getName(),Toast.LENGTH_SHORT).show();
        });
        return holder;
    }

    //第二步：获得传入的list数据，并重写了viewHolder后，
    // 在重写的onBindViewHolder中，把list中的每一个Lesson对象封装绑定到一个ViewHolder中
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Lesson lesson = list.get(position);
        holder.lesosnName.setText(lesson.getName());
        Glide.with(context).load(lesson.getImageId()).into(holder.lessonImage);

        /*向viewHolder绑定监听事件
        if(listener!=null){
            holder.lessonImage.setOnClickListener(view -> {
                int pos = holder.getLayoutPosition();
                //listener.onItemClick(holder.lessonImage,pos);
                listener.onClick(holder,position,list.get(position));
            });
            holder.lessonImage.setOnLongClickListener(view -> {
                int pos = holder.getLayoutPosition();
                listener.onItemLongClick(holder.lesosnName,pos);
                return  false;
            });
        }*/
    }

    @Override
    public int getItemCount(){
        return list.size();
    }
    @Override
    public long getItemId(int position){
        return  position;
    }
    @Override
    public int getItemViewType(int position){
        return position;
    }

    public interface onItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.listener = onItemClickListener;
    }


}
