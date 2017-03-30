package com.diaz.inaki.practica3_contactos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;




/**
 * Created by 8fdi02 on 30/3/17.
 */

public class Modelo {
    private SQLiteDatabase db;
    private Context c;

    public Modelo(Context c) {
        this.c=c;
        db=c.openOrCreateDatabase("MisCumples", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS misCumples(ID integer, TipoNotif char(1), Mensaje VARCHAR(160), Telefono VARCHAR(15), FechaNacimiento VARCHAR(15), Nombre VARCHAR(128)), URIPhoto VARCHAR(128);");
    }




}
