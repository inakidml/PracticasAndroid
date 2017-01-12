package com.diaz.inaki.actividad4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton boton;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton=(ImageButton)findViewById(R.id.imageButton);
        imagen=(ImageView)findViewById(R.id.imageView);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        boton.setImageResource(R.drawable.flat_compass_icon);
        imagen.setImageResource(R.drawable.flat_compass_icon);

    }
}
