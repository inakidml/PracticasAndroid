package com.diaz.inaki.buscaminas;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by inaki on 4/2/17.
 */

//botón sobrecargado con referéncia del tablero y getter de pulsado

public class BotonConTablero extends android.support.v7.widget.AppCompatButton {
    private Tablero tablero;
    private boolean pulsado = false;

    //getter
    public boolean isPulsado() {
        return pulsado;
    }

    public Tablero getTablero() {
        return tablero;
    }

    //setter
    public void setPulsado(boolean pulsado) {

        this.pulsado = pulsado;
    }

    public BotonConTablero(Context context, Tablero tablero) {
        super(context);
        this.tablero = tablero;
    }

}
