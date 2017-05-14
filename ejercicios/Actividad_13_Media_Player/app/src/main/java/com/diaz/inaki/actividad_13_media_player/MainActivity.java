package com.diaz.inaki.actividad_13_media_player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl{
private MediaPlayer mediaPlayer;
    private MediaController mc;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetFileDescriptor resource = getResources().openRawResourceFd(R.raw.julia);
        context = getApplicationContext();
        mediaPlayer = new MediaPlayer();
        mc = new MediaController(context);

        try {
            mediaPlayer.setDataSource(resource);
            mediaPlayer.prepare();
            mc.setMediaPlayer(this);
            mc.setAnchorView(findViewById(R.id.surfaceView));

        } catch (IOException e) {
        } catch (IllegalStateException e) {
        }


    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        try{
            mc.show(0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start() {

        mediaPlayer.start();
    }

    @Override
    public void pause() {
    mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(int i) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
