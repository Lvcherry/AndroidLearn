package com.example.testapplication.Bean;

public class VideoCommentIntroduce {

    private String title;
    private int jump_type;
    private int jump_id;
    public void setTitle(String s){
        this.title=s;
    }
    public void setJump_type(int type){
        this.jump_type=type;
    }
    public void setJump_id(int id){
        this.jump_id=id;
    }
    public String getTitle(){
        return title;
    }
    public int getJump_type(){
        return jump_type;
    }
    public int getJump_id(){
        return jump_id;
    }


}
