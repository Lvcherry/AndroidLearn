package com.example.testapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testapplication.R;
import com.example.testapplication.entity.Story;

import java.util.ArrayList;

public class StoryDetailFragment extends Fragment {

    ArrayList<Story> stories = new ArrayList<Story>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View storyView = inflater.inflate(R.layout.listviewitem,container,false);
        for(int i = 0;i<stories.size();i++) {
            Story story = stories.get(i);
            ((TextView)storyView.findViewById(R.id.ListViewName)).setText(story.getName());
            ((TextView)storyView.findViewById(R.id.ListViewDesc)).setText(story.getDesc());
            ((TextView)storyView.findViewById(R.id.ListViewId)).setText(story.getId());
        }
        return storyView;

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
