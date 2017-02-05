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
    public static int[] casillasNivel = {8, 12, 16};


    public static int tamanio = 8;
    public static int nivel = 1; //nivel 1 principiante 2 amateur 3 avanzado
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

    public void descubrirCeros(int id) {//función que descubre los ceros y sus vecinos

        BotonConTablero b = (BotonConTablero) findViewById(id);

        if (b.isEnabled()) {
            b.setText("" + b.getTablero().getPosicionMinas().get(b.getId()));
            b.setBackgroundResource(R.drawable.button_custom_no_border);
            b.setEnabled(false);

            int j = b.getTablero().getPosicionEnArray()[id][0];
            int k = b.getTablero().getPosicionEnArray()[id][1];
            ;
            int[][] tablero = new int[0][0];
            switch (nivel) {
                case 1:
                    tablero = b.getTablero().getTableroPrincipìante();
                    break;
                case 2:
                    tablero = b.getTablero().getTableroAmateur();
                    break;
                case 3:
                    tablero = b.getTablero().getTableroAvanzado();
                    break;
                default:
                    System.out.println("no es un nivel correcto");
            }

            //recursividad en las adyacentes

            if (j == 0 && k > 0 && k < tablero.length - 1) { // primera fila
                if (tablero[j][k + 1] == 0) { // el de delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k + 1]);
                }
                if (tablero[j][k - 1] == 0) { // el de detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k - 1]);
                }
                if (tablero[j + 1][k] == 0) { // el de debajo
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k]);
                }
                if (tablero[j + 1][k - 1] == 0) { // el de debajo detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k - 1]);
                }
                if (tablero[j + 1][k + 1] == 0) { // el de debajo delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k + 1]);
                }
            }
            if (j == tablero.length - 1 && k > 0 && k < tablero.length - 1) {  // última fila
                if (tablero[j][k + 1] == 0) { // el de delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k + 1]);
                }
                if (tablero[j][k - 1] == 0) { // el de detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k - 1]);
                }
                if (tablero[j - 1][k - 1] == 0) { // el de arriba detras
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k - 1]);
                }
                if (tablero[j - 1][k] == 0) {  // el de arriba
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k]);
                }
                if (tablero[j - 1][k + 1] == 0) { // el de arriba delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k + 1]);
                }
            }
            if (j == 0 && k == 0) { // primera casilla primera fila
                if (tablero[j][k + 1] == 0) { // el de delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k + 1]);
                }
                if (tablero[j + 1][k] == 0) { // el de debajo
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k]);
                }
                if (tablero[j + 1][k + 1] == 0) { // el de debajo delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k + 1]);
                }
            }
            if (j == 0 && k == tablero.length - 1) { //ultima casilla primera fila
                if (tablero[j][k - 1] == 0) { // el de detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k - 1]);
                }
                if (tablero[j + 1][k - 1] == 0) { // el de debajo detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k - 1]);
                }
                if (tablero[j + 1][k] == 0) { // el de debajo
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k]);
                }

            }

            if (j == tablero.length - 1 && k == 0) { // primera casilla última fila
                if (tablero[j][k + 1] == 0) { // el de delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k + 1]);
                }
                if (tablero[j - 1][k] == 0) {  // el de arriba
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j-1][k]);
                }
                if (tablero[j - 1][k + 1] == 0) { // el de arriba delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j-1][k + 1]);
                }
            }
            if (j == tablero.length - 1 && k == tablero.length - 1) { //ultima casilla última fila
                if (tablero[j][k - 1] == 0) { // el de detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k - 1]);
                }

                if (tablero[j - 1][k - 1] == 0) { // el de arriba detras
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j -1][k - 1]);
                }
                if (tablero[j - 1][k] == 0) {  // el de arriba
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j-1][k]);
                }
            }
            if (k == 0 && j > 0 && j < tablero.length - 1) { // primera columna
                if (tablero[j][k + 1] == 0) { // el de delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k + 1]);
                }

                if (tablero[j + 1][k] == 0) { // el de debajo
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j+1][k]);
                }

                if (tablero[j + 1][k + 1] == 0) { // el de debajo delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j+1][k + 1]);
                }
                if (tablero[j - 1][k + 1] == 0) { // el de arriba delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j-1][k + 1]);
                }
                if (tablero[j - 1][k] == 0) {  // el de arriba
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j-1][k]);
                }
            }
            if (k == tablero.length - 1 && j > 0 && j < tablero.length - 1) { // última columna

                if (tablero[j + 1][k] == 0) { // el de debajo
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j+1][k]);
                }

                if (tablero[j - 1][k] == 0) {  // el de arriba
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j-1][k]);
                }
                if (tablero[j][k - 1] == 0) { // el de detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k-1]);
                }
                if (tablero[j - 1][k - 1] == 0) { // el de arriba detras
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j-1][k - 1]);
                }
                if (tablero[j + 1][k - 1] == 0) { // el de debajo detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j+1][k - 1]);
                }
            }

            if (j > 0 && j < tablero.length - 1 && k > 0 && k < tablero.length - 1) { //casillas centrales
                if (tablero[j][k + 1] == 0) { // el de delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k + 1]);
                }
                if (tablero[j][k - 1] == 0) { // el de detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k - 1]);
                }
                if (tablero[j - 1][k - 1] == 0) { // el de arriba detras
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j-1][k - 1]);
                }
                if (tablero[j - 1][k] == 0) {  // el de arriba
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j-1][k]);
                }
                if (tablero[j - 1][k + 1] == 0) { // el de arriba delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j-1][k + 1]);
                }
                if (tablero[j + 1][k] == 0) { // el de debajo
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j+1][k]);
                }
                if (tablero[j + 1][k - 1] == 0) { // el de debajo detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j+1][k - 1]);
                }
                if (tablero[j + 1][k + 1] == 0) { // el de debajo delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j+1][k + 1]);
                }

            }

        }
    }


    @Override
    public void onClick(View view) {

        if (view.getClass().getSimpleName().equals("BotonConTablero")) {
            BotonConTablero b = (BotonConTablero) view;

            switch (b.getTablero().getPosicionMinas().get(b.getId())) {
                case 0:
                    descubrirCeros(b.getId());
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    b.setText("" + b.getTablero().getPosicionMinas().get(b.getId()));
                    break;
                case 9:
                    b.setText("X");
                    break;
                default:
                    System.out.println("No existe esa opción");
            }

        }
    }
}
