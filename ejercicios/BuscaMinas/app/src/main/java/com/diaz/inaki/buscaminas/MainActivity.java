package com.diaz.inaki.buscaminas;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tablero t = new Tablero(1);
        anadirHijos();

    }

    public Point tamanioPantalla() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size;
    }

    public void anadirHijos() {
        Point size = tamanioPantalla();
        int width = (size.x / 16);
        int height = (size.y/ 16);


        GridLayout g = (GridLayout) findViewById(R.id.grid1);

        g.setUseDefaultMargins(false);
        g.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        g.setRowOrderPreserved(false);


        g.setColumnCount(16);
        g.setRowCount(16);

        Button b;
        for (int i = 0; i < 100; i++) {
            b = new Button(this);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(
                    width,
                    height);

            b.setLayoutParams(params);
            b.setPadding(0,0,0,0);

            b.setId(View.generateViewId());//identificador único
            b.setOnClickListener(this);
            b.setText(""+(i+1));

            g.addView(b, i);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
