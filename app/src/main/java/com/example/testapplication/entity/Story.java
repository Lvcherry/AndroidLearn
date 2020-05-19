package com.example.testapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Story implements Parcelable {
    private String name;
    private String desc;
    private String id;
    public Story(){}
    public  Story(String name,String desc ,String id){
        this.name = name;
        this.desc = desc;
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public String getDesc(){
        return this.desc;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){this.name = name;}
    public void setDesc(String desc){this.desc = desc;}
    public void setId(String id){this.id = id;}

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel var1, int var2){
        var1.writeString(name);
        var1.writeString(desc);
        var1.writeString(id);
    }
    public static final Parcelable.Creator<Story> CREATOR = new Parcelable.Creator<Story>(){
        @Override
        public Story createFromParcel(Parcel parcel){
            Story story = new Story();
            story.name = parcel.readString();
            story.desc = parcel.readString();
            story.id = parcel.readString();
            return story;
        }
        @Override
        public Story[] newArray(int size){
            return new Story[size];
        }
    };
}
