package com.diaz.inaki.actividad_13_media_player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {
    private MediaPlayer mediaPlayer;
    private MediaController mc;
    private Handler handler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);
        AssetFileDescriptor resource = getResources().openRawResourceFd(R.raw.julia);
        mc = new MediaController(this){
            @Override
            public void hide() {
                mc.show();

            }
        };
        mc.setMediaPlayer(this);
        mc.setAnchorView(findViewById(R.id.contraintLayout));

        mediaPlayer = new MediaPlayer();


        handler = new Handler();

        try {
            mediaPlayer.setDataSource(resource);
            mediaPlayer.prepare();

        } catch (IOException e) {
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                handler.post(new Runnable() {
                    public void run() {
                        // Se muestra el control en la pantalla. Tras 20 segundos de inactividad, el control se ocultará
                        mc.show(20000);
                    }
                });
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
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
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int i) {
        mediaPlayer.seekTo(i);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }


    @Override
    // El método onTouchEvent nos permite controlar qué hacer cuando el usuario toca la pantalla
    public boolean onTouchEvent(MotionEvent event) {
        // En este caso, cuando el usuario toque la pantalla,
        // mostramos los controles de reproducción
        mc.show();
        return false;
    }

}