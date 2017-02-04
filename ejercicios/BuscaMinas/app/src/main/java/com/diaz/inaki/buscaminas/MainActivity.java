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
public static int tamanio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tamanio=16;
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
        int width = (size.x / tamanio);
        int height = ((size.y-120)/ tamanio);           //correción por la barra de menú (+/- 120)

        GridLayout g = (GridLayout) findViewById(R.id.grid1);

        // pruebas porque se salen los botones
        g.setUseDefaultMargins(false);
        g.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        g.setRowOrderPreserved(false);

        //rejilla de los botones
        g.setColumnCount(tamanio);
        g.setRowCount(tamanio);

        Button b;
        for (int i = 0; i < (tamanio*tamanio); i++) {
            b = new Button(this);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(
                    width,
                    height);

            b.setLayoutParams(params);
            b.setPadding(0,0,0,0);
            b.setText(""+i);
            b.setId(View.generateViewId());//identificador único
            b.setOnClickListener(this);

            g.addView(b, i);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
