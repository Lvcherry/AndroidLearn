package com.example.testapplication.Bean;
import java.util.List;
public class VideoCommentRule {

    private String title;
    List<Content> contentList;
    public void setTitle(String s){
        this.title =s;
    }
    public void setContentList(List<Content> list){
        this.contentList=list;
    }
    public String getTitle(){
        return title;
    }
    public List<Content> getContentList(){
        return contentList;
    }


}
