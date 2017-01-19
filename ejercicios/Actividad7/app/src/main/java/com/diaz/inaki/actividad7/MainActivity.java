package com.diaz.inaki.actividad7;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anadeHijos();
    }
public int randomColor()
    {Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }


    public void anadeHijos() {
        GridLayout g = (GridLayout) findViewById(R.id.grid1);
        Button b;
        for (int i = 0; i < 18; i++) {
            b = new Button(this);
            b.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            b.setText("btn" + (i+1));
            if (b.getText().equals("btn18")){
                b.setText("reset");
            }
            b.setId(View.generateViewId());//identificador único
            b.setOnClickListener(this);
            b.setBackgroundColor(randomColor());
            g.addView(b, i);
        }
    }
    public void resetColor(){
        View v;
        GridLayout g = (GridLayout) findViewById(R.id.grid1);
        for (int i = 0; i < g.getChildCount(); i++){
            v = g.getChildAt(i);
            if(v.getClass().getSimpleName().equals("Button")){

                Button b = (Button) v;
                b.setBackgroundColor(randomColor());
            }
        }
    }
    @Override
    public void onClick(View view) {
        if(view.getClass().getSimpleName().equals("Button")){

            Button b = (Button) view;
            if (b.getText().equals("reset")){

                resetColor();
            }else{ b.setBackgroundColor(Color.WHITE);}


        }
    }
}