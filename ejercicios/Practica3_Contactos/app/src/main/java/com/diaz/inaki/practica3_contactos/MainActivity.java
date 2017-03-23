package com.diaz.inaki.practica3_contactos;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rellenarListaContactos();
    }

    public void rellenarListaContactos() {

        String proyeccion[] = {ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_ID};

        String filtro = ContactsContract.Contacts.DISPLAY_NAME + " like ?";
        String args_filtro[] = {"%"+""+"%"};

        List<String> lista_contactos = new ArrayList<String>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, proyeccion, filtro, args_filtro, null);
        System.out.println(cur.getCount());
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    lista_contactos.add(name);
                }
            }

        }
        cur.close();//Cerramos el cursor
        //Mandamos el adaptador a la lista de contactos

        //  http://www.codigojavalibre.com/2015/10/crear-un-listview-con-imagenes-en-Android-Studio.html
        ListView l = (ListView) findViewById(R.id.listaContactos);
        l.setAdapter(new ArrayAdapter<String>(this, R.layout.fila_lista, lista_contactos));
    }
}
