package com.diaz.inaki.practica3_contactos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


/**
 * Created by 8fdi02 on 30/3/17.
 */

public class Modelo extends AppCompatActivity{
    private SQLiteDatabase db;
    private Context c;

    public Modelo(Context c) {
        this.c=c;
        abrirDb();

    }
    private void abrirDb(){
        db=c.openOrCreateDatabase("MisCumples", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS misCumples(ID integer, TipoNotif char(1), Mensaje VARCHAR(160), Telefono VARCHAR(15), FechaNacimiento VARCHAR(15), Nombre VARCHAR(128), URIPhoto VARCHAR(128));");
    }
    public void aniadirContactoDb(Contacto c){
        db.execSQL("INSERT INTO misCumples VALUES('" + c.getID() + "', '"+ c.getTipoNotif() + "', '"+ c.getMensaje() +"','"+c.getTelefono()+"','"+c.getFechaNacimiento()+"','"+c.getName()+"','"+c.getPhotoURI()+"')");
//        Toast.makeText(this, "Se añadio el contacto " + c.getName().toString(), Toast.LENGTH_LONG).show();
    }
    public void listarDb(){
        Cursor c=db.rawQuery("SELECT * FROM misCumples", null);
        if(c.getCount()==0)
            System.out.println("No hay registros");
        else{
            while(c.moveToNext())
                System.out.println(c.getString(0)+"-"+c.getString(1));
        }
    }

}
