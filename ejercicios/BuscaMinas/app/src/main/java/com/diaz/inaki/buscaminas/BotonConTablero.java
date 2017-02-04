package com.diaz.inaki.buscaminas;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by inaki on 4/2/17.
 */

public class BotonConTablero extends Button {
    private Tablero tablero;

    public BotonConTablero(Context context, Tablero tablero) {
        super(context);
        this.tablero = tablero;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
