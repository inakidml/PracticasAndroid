package com.diaz.inaki.buscaminas;

import android.graphics.Color;
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

    public static boolean DEBUG = true;


    public static int tamanio=8;
    public static int nivel=1; //nivel 1 principiante 2 amateur 3 avanzado
    private Tablero t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seleccionarNivel(2);
        t = new Tablero(nivel);
        anadirBotones();

    }

    public void seleccionarNivel(int n) {// establece nivel y tamaño tablero
        switch (n) {
            case 1: //principiante
                nivel = 1;
                tamanio = 8;
                break;
            case 2: //amateur
                nivel = 2;
                tamanio = 12;
                break;
            case 3: //avanzado
                nivel = 3;
                tamanio = 16;
                break;
            default:
                System.out.println("Nivel erroneo");
        }
    }

    public Point tamanioPantalla() { // nos devuelve el tamaño de pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public void anadirBotones() { // añade los botones
        Point size = tamanioPantalla();
        int width = (size.x / tamanio);
        int height = ((size.y - 120) / tamanio);           //correción por la barra de menú (+/- 120)

        GridLayout g = (GridLayout) findViewById(R.id.grid1);

        // pruebas porque se salen los botones
       /* g.setUseDefaultMargins(false);
        g.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        g.setRowOrderPreserved(false);*/

        //rejilla de los botones
        g.setColumnCount(tamanio);
        g.setRowCount(tamanio);
        int contadorBotones = 0;
        BotonConTablero b;
        for (int i = 0; i < (tamanio * tamanio); i++) {
            b = new BotonConTablero(this, t);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    width,
                    height);

            b.setLayoutParams(params);
            b.setPadding(0, 0, 0, 0);
            b.setId(contadorBotones);//identificador único
            b.setText("");
            b.setBackgroundResource(R.drawable.button_custom);

            b.setOnClickListener(this);
            g.addView(b, i);
            contadorBotones++;
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getClass().getSimpleName().equals("BotonConTablero")){
            BotonConTablero b = (BotonConTablero) view;
            
            b.setText("" + b.getTablero().getPosicionMinas().get(b.getId()));
        }
    }
}
