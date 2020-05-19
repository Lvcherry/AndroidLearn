package com.example.testapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapplication.Adapter.JsonListViewAdapter;
import com.example.testapplication.Bean.CommentBean;
import com.example.testapplication.Bean.CommentNoticeAll;
import com.example.testapplication.Bean.CommentTag;
import com.example.testapplication.Bean.Content;
import com.example.testapplication.Bean.VideoCommentIntroduce;
import com.example.testapplication.Bean.VideoCommentRule;
import com.example.testapplication.BeanPackage.JsonRootBean;
import com.example.testapplication.entity.JsonData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonActivity extends AppCompatActivity {
    List<JsonData> list = new ArrayList<JsonData>();
    @Override
    protected void onCreate(Bundle saveInstanceState)  {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.jsonexplain);

        /*获得json字符串*/
        String jsonString = getJson("test.json",this);

        /*gson利用Json字符串生成Javabean*/
        JsonRootBean bean = new Gson().fromJson(jsonString,JsonRootBean.class);

        /*简单的手动解析 解析到一个stringBuilder
        List<String> list = parseJSONWithJSONObject(jsonString);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<list.size();i++){
            stringBuilder.append(list.get(i));
        }*/

        /*手动解析获得一个commentBean*/
        CommentBean bean1 = getDataFromStringToBean(jsonString);

        /*单独的textView测试输出
        TextView textView = findViewById(R.id.et_key);
        textView.setText(bean1.getCommandRule());
        */

        /*把json数据解析成List<JsonData>的形式，方便进行list view和recyclerView的内容填充*/
        list = getJsonDataListFromString(jsonString);

        /*自定义list view展示解析好的json数据 List<JsonData> list
        JsonListViewAdapter adapter = new JsonListViewAdapter(JsonActivity.this,R.layout.jsonlistviewitem,list);
        ListView listView = findViewById(R.id.jsonListView);
        listView.setAdapter(adapter);
        */


        /*自定义recyclerView并采用瀑布流layoutManager展示解析好的json数据  List<JsonData> list
        RecyclerView recyclerView = findViewById(R.id.jsonRecyclerView);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        JsonRecyclerViewAdapter adapter1 = new JsonRecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter1);
        */

        /*自定义listview 展示解析好的json数据List<tag>(标准手动解析)*/
        List<CommentTag> tagList = getTagListFromString(jsonString);
        beanListViewAdapter adapter = new beanListViewAdapter(JsonActivity.this,R.layout.jsonlistviewitem,tagList);
        ListView listView = findViewById(R.id.jsonListView);
        listView.setAdapter(adapter);




    }
    //readLine方法
    public String getJson(String fileName, Context context){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            AssetManager assetManager = context.getAssets();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName),"UTF-8"));
            String line;
            while((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);
                Log.d("aaa",line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    //int Count+char[]方法
    public String getJson2(String fileName,Context context){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            AssetManager assetManager = context.getAssets();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName),"UTF-8"));

            int count;
            char[] buf= new char[65534];
            while((count = bufferedReader.read())>0){
                stringBuilder.append(buf,0,count);
                System.out.println(stringBuilder);
                //Log.d("aaa",stringBuilder.toString());
            }
        }catch (IOException E){
            E.printStackTrace();
        }
        return stringBuilder.toString();
    }

    //手动解析一点点数据
    private List<String> parseJSONWithJSONObject(String string){
        List<String> list = new ArrayList<String>();
        try{
            JSONObject jsonObject = new JSONObject(string);
            list.add(jsonObject.getString("app_development"));
            JSONArray array = jsonObject.getJSONObject("privacy_policy").getJSONArray("paragraph");
            for(int i=0;i<array.length();i++) {
                JSONObject object = array.getJSONObject(i);
                list.add(object.getString("title")+":");
                list.add(object.getString("Content"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


    //手动解析大量数据到hashMap,再对hashMap进行处理，得到向list view/recyclerview填充数据的list<JsonData>
    private List<JsonData> getJsonDataListFromString(String JsonString){
        List<JsonData> list = new ArrayList<JsonData>();
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        try{
            JSONObject rootObject = new JSONObject(JsonString);
            hashMap.put("errorno",rootObject.getString("errorno"));
            hashMap.put("tips_type",rootObject.getString("tips_type"));

            JSONObject showObject = rootObject.getJSONObject("show");
            hashMap.put("show.showRule",showObject.getString("ShowRule"));
            hashMap.put("show.showAtlasMaxNumber",showObject.getString("ShowAtlasMaxNumber"));
            hashMap.put("show.ShowAtlasMinNumber",showObject.getString("ShowAtlasMinNumber"));
            hashMap.put("show.ShowMaxLength",showObject.getString("ShowMaxLength"));
            hashMap.put("show.ShowMinLength",showObject.getString("ShowMinLength"));
            hashMap.put("show.ShowNotice",showObject.getString("ShowNotice"));
            hashMap.put("show.TaskShowRule",showObject.getString("TaskShowRule"));

            JSONObject commentObject = rootObject.getJSONObject("comment");
            hashMap.put("comment.CommentRule",commentObject.getString("CommentRule"));
            hashMap.put("comment.CommentNotice",commentObject.getString("CommentNotice"));
            hashMap.put("comment.CommentNoticeAll",commentObject.getJSONArray("CommentNoticeAll").getString(0));

            JSONObject comment_videoComment_Object = commentObject.getJSONObject("VideoCommentRule");
            hashMap.put("comment.VideoCommentRule.title",comment_videoComment_Object.get("title"));
            JSONArray comment_videoComment_content_array = comment_videoComment_Object.getJSONArray("Content");
            for(int i = 0;i<comment_videoComment_content_array.length();i++){
                hashMap.put("comment.VideoCommentRule.Content"+i,comment_videoComment_content_array.getString(i));
            }
            JSONArray comment_commentTag = commentObject.getJSONArray("CommentTag");
            for(int i=0;i<comment_commentTag.length();i++){
                JSONObject comment_commentTag_no = comment_commentTag.getJSONObject(i);
                hashMap.put("comment:tag_id/tag_name"+i,comment_commentTag_no.getString("tag_id")+"/"+comment_commentTag_no.getString("tag_name"));
            }
            hashMap.put("app_development",rootObject.getString("app_development"));
            hashMap.put("ALiYunQuickLoginToken",rootObject.getString("ALiYunQuickLoginToken"));



        }catch (Exception e){
            e.printStackTrace();
        }
        /*对hashMap进行处理，得到list<JsonData>*/
        for(int i = 0; i<hashMap.size();i++){
            JsonData data = new JsonData();
            data.setKey((String)(hashMap.keySet().toArray())[i]);
            data.setValue((String)hashMap.get((hashMap.keySet().toArray())[i]));
            list.add(data);
        }
        return list;
    }

        /*手动解析出来一个bean ，但对于list view显示填充数据不符合list*/
    public CommentBean getDataFromStringToBean(String jsonString ){
        CommentBean bean = new CommentBean();
        try{
            JSONObject rootObject= new JSONObject(jsonString);
            JSONObject commentObject = rootObject.getJSONObject("comment");
            bean.setCommandRule(commentObject.getString("CommentRule"));
            bean.setCommentAtlasMaxNumber(Integer.valueOf(commentObject.getString("CommentAtlasMaxNumber")));
            bean.setCommentAtlasMinNumber(Integer.valueOf(commentObject.getString("CommentAtlasMinNumber")));
            bean.setCommentMaxLength(Integer.valueOf(commentObject.getString("CommentMaxLength")));
            bean.setCommentMinLength(Integer.valueOf(commentObject.getString("CommentMinLength")));
            bean.setCommentNotice(commentObject.getString("CommentNotice"));
            bean.setVideoWaterMark(commentObject.getString("VideoWaterMark"));
            bean.setCommentMaxRewardDesc(commentObject.getString("CommentMaxRewardDesc"));
            bean.setVideoCommentTitle(commentObject.getString("VideoCommentTitle"));
            bean.setVideoCommentError(commentObject.getString("VideoCommentError"));

            List<CommentNoticeAll> commentNoticeAllList = new ArrayList<CommentNoticeAll>();
            CommentNoticeAll commentNoticeAll = new CommentNoticeAll();
            commentNoticeAll.setAllNotice(commentObject.getJSONArray("CommentNoticeAll").getString(0));
            commentNoticeAllList.add(commentNoticeAll);
            bean.setCommentNoticeAllList(commentNoticeAllList);

            JSONObject videoCommentRuleObject = commentObject.getJSONObject("VideoCommentRule");
            JSONArray videoCommentRuleCotentList = videoCommentRuleObject.getJSONArray("Content");
            List<Content> contentList = new ArrayList<Content>();
            for(int i = 0;i<videoCommentRuleCotentList.length();i++){
                Content tempContent = new Content();
                tempContent.setContent(videoCommentRuleCotentList.getString(i));
                contentList.add(tempContent);
            }
            VideoCommentRule videoCommentRule = new VideoCommentRule();
            videoCommentRule.setContentList(contentList);
            videoCommentRule.setTitle(videoCommentRuleObject.getString("title"));
            bean.setVideoCommentRule(videoCommentRule);

            JSONObject videoCommentIntroduceObject = commentObject.getJSONObject("VideoCommentIntroduce");
            VideoCommentIntroduce videoCommentIntroduce = new VideoCommentIntroduce();
            videoCommentIntroduce.setJump_id(Integer.valueOf(videoCommentIntroduceObject.getString("jump_id")));
            videoCommentIntroduce.setJump_type(Integer.valueOf(videoCommentIntroduceObject.getString("jump_type")));
            videoCommentIntroduce.setTitle(videoCommentIntroduceObject.getString("title"));
            bean.setVideoCommentIntroduce(videoCommentIntroduce);

            JSONArray commentTagArray = commentObject.getJSONArray("CommentTag");
            List<CommentTag> tagList = new ArrayList<CommentTag>();
            for(int i  = 0;i<commentTagArray.length();i++){
                JSONObject commentTagObject = commentTagArray.getJSONObject(i);
                CommentTag tag = new CommentTag();
                tag.setTag_id(Integer.valueOf(commentTagObject.getString("tag_id")));
                tag.setTag_name(commentTagObject.getString("tag_name"));
                tagList.add(tag);
            }
            bean.setCommentTagList(tagList);

        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }


    public List<CommentTag> getTagListFromString(String jsonString ){
        List<CommentTag> tagList = new ArrayList<CommentTag>();
        try {
            JSONObject rootObject = new JSONObject(jsonString);
            JSONObject commentObject = rootObject.getJSONObject("comment");
            JSONArray commentTagArray = commentObject.getJSONArray("CommentTag");
            for(int i  = 0;i<commentTagArray.length();i++){
                JSONObject commentTagObject = commentTagArray.getJSONObject(i);
                CommentTag tag = new CommentTag();
                tag.setTag_id(Integer.valueOf(commentTagObject.getString("tag_id")));
                tag.setTag_name(commentTagObject.getString("tag_name"));
                tagList.add(tag);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  tagList;
    }

    class beanListViewAdapter extends ArrayAdapter{
        private final int resourceId;
        public beanListViewAdapter(Context context, int resource, List<CommentTag> object){
            super(context,resource,object);
            resourceId = resource;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            CommentTag data = (CommentTag) getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            TextView key = view.findViewById(R.id.tv_json_key);
            TextView value = view.findViewById(R.id.tv_json_value);
            key.setText(String.valueOf(data.getTag_id()));
            value.setText(data.getTag_name());
            return view;
        }
    }




}
