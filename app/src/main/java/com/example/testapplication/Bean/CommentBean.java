package com.example.testapplication.Bean;

import  java.util.List;
public class CommentBean {

    private String CommandRule;
    private  int CommentAtlasMaxNumber ;
    private int CommentAtlasMinNumber;
    private int CommentMaxLength;
    private int CommentMinLength;
    private String CommentNotice;
    private List<CommentNoticeAll> commentNoticeAllList;
    private String VideoWaterMark;
    private String CommentMaxRewardDesc;
    private VideoCommentRule videoCommentRule;
    private String VideoCommentTitle;
    private String VideoCommentError;
    private VideoCommentIntroduce videoCommentIntroduce;
    private List<CommentTag> commentTagList;

    public void setCommandRule(String s){this.CommandRule = s;}
    public void setCommentAtlasMaxNumber(int i){this.CommentAtlasMaxNumber=i;}
    public void setCommentAtlasMinNumber(int i){this.CommentAtlasMinNumber=i;}
    public void setCommentMaxLength(int i){this.CommentMaxLength=i;}
    public void setCommentMinLength(int i){this.CommentMinLength=i;}
    public void setCommentNotice(String s){this.CommentNotice=s;}
    public void setCommentNoticeAllList(List<CommentNoticeAll> list){this.commentNoticeAllList=list;}
    public void setVideoWaterMark(String s){this.VideoWaterMark = s;}
    public void setCommentMaxRewardDesc(String s){this.CommentMaxRewardDesc=s;}
    public void setVideoCommentRule(VideoCommentRule rule){this.videoCommentRule=rule;}
    public void setVideoCommentTitle(String s){this.VideoCommentTitle=s;}
    public void setVideoCommentError(String s){this.VideoCommentError=s;}
    public void setVideoCommentIntroduce(VideoCommentIntroduce introduce){this.videoCommentIntroduce =introduce;}
    public void setCommentTagList(List<CommentTag> list){this.commentTagList=list;}

    public String getCommandRule(){return CommandRule;}
    public int getCommentAtlasMaxNumber(){return CommentAtlasMaxNumber;}
    public int getCommentAtlasMinNumber(){return CommentAtlasMinNumber;}
    public int getCommentMaxLength(){return CommentMaxLength;}
    public int getCommentMinLength(){return CommentMinLength;}
    public String getCommentNotice(){return CommentNotice;}
    public List<CommentNoticeAll> getCommentNoticeAllList(){return commentNoticeAllList;}
    public String getVideoWaterMark(){return VideoWaterMark ;}
    public String getCommentMaxRewardDesc(){return CommentMaxRewardDesc;}
    public VideoCommentRule getVideoCommentRule(){return videoCommentRule;}
    public String getVideoCommentTitle(){return VideoCommentTitle;}
    public String getVideoCommentError(){return VideoCommentError;}
    public VideoCommentIntroduce getVideoCommentIntroduce(){return videoCommentIntroduce ;}
    public List<CommentTag> getCommentTagList(){return commentTagList;}


}
