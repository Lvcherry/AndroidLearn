package com.example.testapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class MusicActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.music_layout);
        Button play = findViewById(R.id.playMusic);
        Button pause = findViewById(R.id.pauseMusic);
        Button stop = findViewById(R.id.stopMusic);
        if(ContextCompat.checkSelfPermission(MusicActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MusicActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
        }else {
            initMusicPlayer();
        }
        play.setOnClickListener(view -> {
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
        });
        pause.setOnClickListener(view -> {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        });
        stop.setOnClickListener(view -> {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.reset();
                initMusicPlayer();
            }
        });

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void initMusicPlayer(){
        try{
            File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permission,int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    initMusicPlayer();
                }else {
                    Toast.makeText(this,"拒绝访问",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
        }
    }
}
