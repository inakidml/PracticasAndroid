package com.example.a8fdi02.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button miBoton;
        miBoton = (Button) findViewById(R.id.miBoton);
        miBoton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        TextView miTexto;
        miTexto = (TextView) findViewById(R.id.textLabel);
        miTexto.setText("Probando....");
    }
}
