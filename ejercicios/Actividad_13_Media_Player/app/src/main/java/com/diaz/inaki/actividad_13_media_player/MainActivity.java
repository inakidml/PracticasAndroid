package com.diaz.inaki.actividad_13_media_player;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetFileDescriptor resource = getResources().openRawResourceFd(R.raw.julia);

        MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(resource);
            mediaPlayer.prepare();
        } catch (IOException e) {
        }

        mediaPlayer.start();
    }
}
