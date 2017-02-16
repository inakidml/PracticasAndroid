package com.diaz.inaki.buscaminas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    public static boolean DEBUG = true;
    public static int[] casillasNivel = {8, 12, 16};


    public static int tamanio = 8; // tamanio del tablero
    public static int nivel = 1; //nivel para primer tablero, 1 principiante 2 amateur 3 avanzado
    private Tablero t; //un tablero
    private int contadorMinas = 0;
    private int icono = 1; // 1 bomba, 2 seta, 3 bayas

    private Menu refMenu; //referencia para poder cambiar el icono del menú

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepararTablero(nivel);
    }

    public void prepararTablero(int nivel) {
        GridLayout g = (GridLayout) findViewById(R.id.grid1);
        g.removeAllViews();
        seleccionarNivel(nivel);
        t = new Tablero(nivel);
        contadorMinas = 0;
        anadirBotones();
    }

    //función para mostrar diálogo al acabar el juego
    public void finDeJuego(int mensaje, int titulo) {
        GridLayout g = (GridLayout) findViewById(R.id.grid1);
        int childNum = g.getChildCount();
        for (int i = 0; i < childNum; i++) {
            g.getChildAt(i).setEnabled(false);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(mensaje);
        builder.setTitle(titulo);
        //Añadir Botón OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.dismiss();
            }
        });
        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflar Menú
        //http://www.sgoliver.net/blog/menus-en-android-i-conceptos-basicos/

        getMenuInflater().inflate(R.menu.menu, menu);
        refMenu = menu;
        return true;
    }

    // Opción pulsada en el menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1://Dialogo de Instrucciones
                // https://developer.android.com/guide/topics/ui/dialogs.html?hl=es

                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message);
                builder.setTitle(R.string.dialog_title);
                //Añadir Botón OK
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        dialog.dismiss();
                    }
                });
                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            case R.id.MnuOpc2: // Comienza el juego

                prepararTablero(nivel);

                return true;
            case R.id.MnuOpc3:  //seleccionar nivel
                System.out.println("Opcion 3 pulsada!");

                // Creating and Building the Dialog
                AlertDialog.Builder builderNivel = new AlertDialog.Builder(this);
                builderNivel.setTitle("Select The Difficulty Level");
                CharSequence[] items = {" Principiante ", " Amateur ", " Avanzado "};
                builderNivel.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        switch (item) {
                            case 0:
                                prepararTablero(1);
                                break;
                            case 1:
                                prepararTablero(2);
                                break;
                            case 2:
                                prepararTablero(3);
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                final AlertDialog levelDialog;
                levelDialog = builderNivel.create();
                levelDialog.show();
                return true;
            case R.id.MnuOpc4:
                //spinner en alertdialog para seleccionar imagen

                Spinner spinner = new Spinner(this, Spinner.MODE_DIALOG);
                CustomSpinner adapter = new CustomSpinner(this,
                        new Integer[]{R.drawable.blanco_50px, R.drawable.bomb_50px, R.drawable.mushroom_50px, R.drawable.baya_frambu_50px});
                //meto el primer drawable en blanco, ya que siempre esta seleccionado el 0 por defecto
                spinner.setAdapter(adapter);
                final AlertDialog spinnerDialog;
                AlertDialog.Builder spinnerBuilder = new AlertDialog.Builder(this);
                spinnerBuilder.setTitle("Select the icon");
                spinnerDialog = spinnerBuilder.create();
                spinnerDialog.setView(spinner);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //para cambiar el icono del menú
                        MenuItem menuOp4 = refMenu.findItem(R.id.MnuOpc4);
                        switch (position) {
                            case 0:
                                break;
                            case 1:
                                icono = 1;
                                spinnerDialog.dismiss();
                                menuOp4.setIcon(R.drawable.bomb);
                                break;
                            case 2:
                                icono = 2;
                                spinnerDialog.dismiss();
                                menuOp4.setIcon(R.drawable.mushroom_super_24680);
                                break;
                            case 3:
                                icono = 3;
                                spinnerDialog.dismiss();
                                menuOp4.setIcon(R.drawable.baya_frambu);
                                break;
                        }
                    } // to close the onItemSelected

                    public void onNothingSelected(AdapterView<?> parent) {
                        spinnerDialog.dismiss();
                    }
                });

                spinnerDialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void seleccionarNivel(int n) {// establece nivel y tamaño tablero y actualiza variables
                                         // es llamada desede preparar tablero
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

    public int getStatusBarHeight() { //función para saber la altura de la barra de notificaciones
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public int getActionBarEight() {//función para saber la altura de la actionBar
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public void anadirBotones() { // añade los botones según el tamanio del tablero

        // Calculate ActionBar height
        int actionBarHeight = getActionBarEight();
        int statusBarHeight = getStatusBarHeight();
        if (DEBUG) {
            System.out.println("statusbar eight= " + statusBarHeight);
            System.out.println("actionbar eight= " + actionBarHeight);
        }
        Point size = tamanioPantalla(); //tamaño de pantalla
        int width = (size.x / tamanio);
        int height = ((size.y - (actionBarHeight + statusBarHeight)) / tamanio);//correción por la barra de menú y notificaciones

        GridLayout g = (GridLayout) findViewById(R.id.grid1);


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
            b.setOnLongClickListener(this);
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
            int[][] tablero = new int[0][0]; // Array temporal
            switch (nivel) {
                case 1:
                    tablero = b.getTablero().getTableroPrincipìante(); //asignamos referncia del tablero que estamos usando
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
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k]);
                }
                if (tablero[j - 1][k + 1] == 0) { // el de arriba delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k + 1]);
                }
            }
            if (j == tablero.length - 1 && k == tablero.length - 1) { //ultima casilla última fila
                if (tablero[j][k - 1] == 0) { // el de detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k - 1]);
                }

                if (tablero[j - 1][k - 1] == 0) { // el de arriba detras
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k - 1]);
                }
                if (tablero[j - 1][k] == 0) {  // el de arriba
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k]);
                }
            }
            if (k == 0 && j > 0 && j < tablero.length - 1) { // primera columna
                if (tablero[j][k + 1] == 0) { // el de delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k + 1]);
                }

                if (tablero[j + 1][k] == 0) { // el de debajo
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k]);
                }

                if (tablero[j + 1][k + 1] == 0) { // el de debajo delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k + 1]);
                }
                if (tablero[j - 1][k + 1] == 0) { // el de arriba delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k + 1]);
                }
                if (tablero[j - 1][k] == 0) {  // el de arriba
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k]);
                }
            }
            if (k == tablero.length - 1 && j > 0 && j < tablero.length - 1) { // última columna

                if (tablero[j + 1][k] == 0) { // el de debajo
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k]);
                }

                if (tablero[j - 1][k] == 0) {  // el de arriba
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k]);
                }
                if (tablero[j][k - 1] == 0) { // el de detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j][k - 1]);
                }
                if (tablero[j - 1][k - 1] == 0) { // el de arriba detras
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k - 1]);
                }
                if (tablero[j + 1][k - 1] == 0) { // el de debajo detrás
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j + 1][k - 1]);
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
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k - 1]);
                }
                if (tablero[j - 1][k] == 0) {  // el de arriba
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k]);
                }
                if (tablero[j - 1][k + 1] == 0) { // el de arriba delante
                    descubrirCeros(b.getTablero().getArrayEnPosicion()[j - 1][k + 1]);
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

        }
    }


    @Override
    public void onClick(View view) {

        if (view.getClass().getSimpleName().equals("BotonConTablero")) {
            BotonConTablero b = (BotonConTablero) view;
            if (!b.isPulsado()) { // si el botón no esta pulsado...
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
                        b.setBackgroundResource(R.drawable.button_pressed_explosion);
                        finDeJuego(R.string.dialog_fin, R.string.dialog_fin_title);
                        break;
                    default:
                        System.out.println("No existe esa opción");
                }
            }
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getClass().getSimpleName().equals("BotonConTablero")) {
            BotonConTablero b = (BotonConTablero) view;

            switch (b.getTablero().getPosicionMinas().get(b.getId())) {
                case 0:
                    b.setText("" + b.getTablero().getPosicionMinas().get(b.getId()));
                    finDeJuego(R.string.dialog_fin, R.string.dialog_fin_title);
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
                    finDeJuego(R.string.dialog_fin, R.string.dialog_fin_title);
                    break;
                case 9:
                    switch (icono) {
                        case 1:
                            b.setBackgroundResource(R.drawable.button_pressed_image_bomb);
                            break;
                        case 2:
                            b.setBackgroundResource(R.drawable.button_pressed_image_mushroom);
                            break;
                        case 3:
                            b.setBackgroundResource(R.drawable.button_pressed_image_baya);
                            break;
                    }

                    contadorMinas++;
                    switch (nivel) {
                        case 1:
                            if (contadorMinas >= b.getTablero().getPRINCIPIANTE()[2]) {
                                finDeJuego(R.string.dialog_fin_win, R.string.dialog_fin_win_title);
                            }
                            break;
                        case 2:
                            if (contadorMinas >= b.getTablero().getAMATEUR()[2]) {
                                finDeJuego(R.string.dialog_fin_win, R.string.dialog_fin_win_title);
                            }
                            break;
                        case 3:
                            if (contadorMinas >= b.getTablero().getAVANZADO()[2]) {
                                finDeJuego(R.string.dialog_fin_win, R.string.dialog_fin_win_title);
                            }
                            break;
                        default:
                            System.out.println("Nivel mal");
                    }


                    b.setPulsado(true);
                    break;
                default:
                    System.out.println("No existe esa opción");
            }

        }


        return false;
    }
}


