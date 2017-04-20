package com.diaz.inaki.practica3_contactos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 8fdi02 on 30/3/17.
 */

public class Modelo extends AppCompatActivity {
    private SQLiteDatabase db;
    private Context c;
    private List<Contacto> listaContactos;
    private Map<String, Integer> listaIdsBd; //lista para saber cuales estan en la BD


    public Modelo(Context c) {
        this.c = c;
        listaContactos = new ArrayList<Contacto>();
        listaIdsBd = new HashMap<>();
        crearTabla();
        cargarBD();
        listarDb();


    }


    private void abrirDb() {
        db = c.openOrCreateDatabase("MisCumples", Context.MODE_PRIVATE, null);

    }

    private void cerrarDB() {
        db.close();
    }

    private void crearTabla() {
        abrirDb();
        db.execSQL("CREATE TABLE IF NOT EXISTS misCumples(ID integer, TipoNotif char(1), Mensaje VARCHAR(160), Telefono VARCHAR(15), FechaNacimiento VARCHAR(15), Nombre VARCHAR(128), URIPhoto VARCHAR(128));");
        cerrarDB();
    }


    //Añadir contacto a DB
    public void aniadirContactoDb(Contacto c) {
        abrirDb();
        db.execSQL("INSERT INTO misCumples VALUES('" + c.getID() + "', '" + c.getTipoNotif() + "', '" + c.getMensaje() + "','" + c.getTelefono() + "','" + c.getFechaNacimiento() + "','" + c.getName() + "','" + c.getPhotoURI() + "')");

        listaContactos.add(c);
        listaIdsBd.put(c.getID(),  listaContactos.indexOf(c));
        cerrarDB();
    }

    //Cargar contactos de BD en arraylist
    public void cargarBD() {
        abrirDb();
        Cursor c = db.rawQuery("SELECT * FROM misCumples", null);
        if (c.getCount() == 0)
            System.out.println("No hay registros en BD");
        else {
            listaIdsBd.clear();
            while (c.moveToNext()) {

                Contacto contactoTemp = new Contacto();

                contactoTemp.setID(c.getString(0));
                contactoTemp.setTipoNotif(c.getString(1).charAt(0));
                contactoTemp.setMensaje(c.getString(2));
                contactoTemp.setTelefono(c.getString(3));
                contactoTemp.setFechaNacimiento(c.getString(4));
                contactoTemp.setName(c.getString(5));
                contactoTemp.setPhotoURI(c.getString(6));

                listaContactos.add(contactoTemp);
                getListaIdsBd().put(c.getString(0), listaContactos.indexOf(contactoTemp));
            }
        }
        cerrarDB();
    }

    public void listarDb() {
        abrirDb();
        Cursor c = db.rawQuery("SELECT * FROM misCumples", null);
        if (c.getCount() == 0)
            System.out.println("No hay registros en BD");
        else {
            while (c.moveToNext()) {
                System.out.println(c.getString(0) + "-" + c.getString(1) + "-" + c.getString(2) + "-" + c.getString(3) + "-" + c.getString(4) + "-" + c.getString(5) + "-" + c.getString(6));
            }
        }
        cerrarDB();
    }

    //Borrar contacto de DB
    public void borrarContactoDb(Contacto c) {
        db.execSQL("DELETE FROM misCumples WHERE ID = '" + c.getID() + "'");
        listaIdsBd.remove(c.getID());
    }
    public void OJOborrarDB(){
        abrirDb();
        db.execSQL("DELETE FROM misCumples");
        cerrarDB();
    }

    public void modificarContactoDB(Contacto c, Contacto cGuardado) {
        abrirDb();
        String id = c.getID();
        db.execSQL("UPDATE misCumples SET TipoNotif ='"+c.getTipoNotif()+"', Mensaje ='"+c.getMensaje()+"', Telefono = '"+c.getTelefono()+"', FechaNacimiento = '"+c.getFechaNacimiento()+"', Nombre = '"+c.getName()+"', URIPhoto = '"+c.getPhotoURI()+"' WHERE ID = '"+id+"'");
        cerrarDB();
        //sustituir en el arraylist
        //System.out.println("index del arraylist = "+ getListaIdsBd().get(cGuardado.getID()));
        getListaContactos().set(getListaIdsBd().get(cGuardado.getID()), c);
    }


    //Getters y Setters


    public List<Contacto> getListaContactos() {
        return listaContactos;
    }

    public Map<String, Integer> getListaIdsBd() {
        return listaIdsBd;
    }
}
