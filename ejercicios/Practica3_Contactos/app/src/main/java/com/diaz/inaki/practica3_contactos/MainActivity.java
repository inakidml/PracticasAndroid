package com.diaz.inaki.practica3_contactos;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Modelo mod;
    private List<Contacto> listaContactosPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mod = new Modelo(this);
        mod.listarDb();
        rellenarListaContactos();
        Context context = this;
        mod.listarDb();


    }

    public void rellenarListaContactos() {

        String proyeccion[] = {ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_URI};

        String filtro = ContactsContract.Contacts.DISPLAY_NAME + " like ?";
        String args_filtro[] = {"%" + "" + "%"};

        listaContactosPhone = new ArrayList<Contacto>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, proyeccion, filtro, args_filtro, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String imageURI = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));

                //Conseguir Fecha nacimiento

                //http://stackoverflow.com/questions/8579883/get-birthday-for-each-contact-in-android-application/8638744

                //preparamos cursor filtrando con el ID del contacto
                //ojo URI de Data, no de Contacts
                Uri uri = ContactsContract.Data.CONTENT_URI;

                String[] projection = new String[]{
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Event.CONTACT_ID,
                        ContactsContract.CommonDataKinds.Event.START_DATE};
                //filtramos por id de contacto y tipo de fecha, cumpleaños
                String where =
                        ContactsContract.Data.MIMETYPE + "= ? AND " +
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id + " AND " +
                                ContactsContract.CommonDataKinds.Event.TYPE + "=" +
                                ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY;
                String[] selectionArgs = new String[]{
                        ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE};

                String sortOrder = null;

                Cursor birthCursor = getContentResolver().query(uri, projection, where, selectionArgs, sortOrder);
                //¿Cual es la columna de la fecha?
                int bDayColumn = birthCursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE);

                String bDay = "vacío";
                while (birthCursor.moveToNext()) {
                    //conseguimos la fecha
                    bDay = birthCursor.getString(bDayColumn);
                }
                //cerramos cursor
                birthCursor.close();

                //conseguir el número
                String telefono = "vacío";
                Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                while (phones.moveToNext()) {
                    String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));

                    switch (type) {
                        case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                            // do something with the Home number here...
                            break;
                        case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                            telefono = number;
                            break;
                        case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                            // do something with the Work number here...
                            break;
                    }
                }
                phones.close();


                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Contacto c = new Contacto();
                    c.setName(name);
                    c.setTipoNotif('a');
                    c.setTelefono(telefono);
                    c.setPhotoURI(imageURI);
                    c.setFechaNacimiento(bDay);
                    listaContactosPhone.add(c);
                    mod.aniadirContactoDb(c);

                }
            }
        }
        cur.close();//Cerramos el cursor

        //Mandamos el adaptador a la lista de contactos

        //  http://www.codigojavalibre.com/2015/10/crear-un-listview-con-imagenes-en-Android-Studio.html

        ListView l = (ListView) findViewById(R.id.listaContactos);
        l.setAdapter(new CustomListAdapter(this, listaContactosPhone));
    }


}
