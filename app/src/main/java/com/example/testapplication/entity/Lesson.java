package com.example.testapplication.entity;

import java.io.Serializable;

public class Lesson  implements Serializable  {
    private String name;
    private int imageId;
    public Lesson(String name,int imageId){
        this.imageId =imageId;
        this.name=name;
    }
    public void setName(String s){
        this.name=s;
    }
    public void setImageId(int i){
        this.imageId = i;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }

}
