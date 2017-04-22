package com.diaz.inaki.practica3_contactos;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLOutput;

public class VerContactoActivity extends AppCompatActivity implements View.OnClickListener {
    private Contacto contactoOriginal;
    private Contacto c;
    private CheckBox aviso;
    private EditText mensaje;
    private static final int CONTACT_REQUEST = 2;  //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //System.out.println("onCreate verDetalles");
        setContentView(R.layout.activity_ver_contacto);
        Button botonGuardar = (Button) findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(this);
        Button botonDetalles = (Button) findViewById(R.id.botonDetalles);
        botonDetalles.setOnClickListener(this);
        //System.out.println(botonDetalles.getClass().getSimpleName());
        //System.out.println(botonGuardar.getClass().getSimpleName());

        contactoOriginal = (Contacto) getIntent().getSerializableExtra("Contacto");
        //Clonamos el contacto para devolver original y modificado para saber si ha habido cambios
        c = new Contacto();
        c.setName(contactoOriginal.getName());
        c.setID(contactoOriginal.getID());
        c.setFechaNacimiento(contactoOriginal.getFechaNacimiento());
        c.setPhotoURI(contactoOriginal.getPhotoURI());
        c.setMensaje(contactoOriginal.getMensaje());
        c.setTelefono(contactoOriginal.getTelefono());
        c.setTipoNotif(contactoOriginal.getTipoNotif());

        ImageView photo = (ImageView) findViewById(R.id.photoVerContacto);
        TextView nombre = (TextView) findViewById(R.id.textoNombreVerContacto);
        TextView telefono = (TextView) findViewById(R.id.textoTelefonoVerContacto);
        aviso = (CheckBox) findViewById(R.id.checkBoxVerContacto);
        TextView fechaNacimiento = (TextView) findViewById(R.id.fechaNacimientoVerContacto);
        mensaje = (EditText) findViewById(R.id.mensajeVerContacto);


        photo.setImageURI(Uri.parse(c.getPhotoURI()));
        nombre.setText(c.getName());
        telefono.setText(c.getTelefono());
        fechaNacimiento.setText(c.getFechaNacimiento());
        mensaje.setHint(c.getMensaje());
        if(c.getTipoNotif()=='y'){
            aviso.setChecked(true);
        }
        //TODO aviso, mensaje

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.botonGuardar:
                c.setMensaje(leerMensaje(mensaje));
                c.setTipoNotifBoolean(aviso.isChecked());
                Intent i = new Intent();
                i.putExtra("Contacto Original", contactoOriginal);
                i.putExtra("Contacto Modificado", c);
                setResult(RESULT_OK, i);
                finish();
                break;
            case R.id.botonDetalles:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(c.getID()));
                intent.setData(uri);
                startActivityForResult(intent, CONTACT_REQUEST);
                break;
            default:
                System.out.println("No se reconoce botón");
        }

        //TODO guardar

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //https://developer.android.com/training/basics/intents/result.html
        // Check which request we're responding to
        if (requestCode == CONTACT_REQUEST) {
            // Make sure the request was successful


            /*Al volver dandole a la flecha para atrás, el resultado es RESULT_CANCELLED (0)
            //System.out.println(resultCode);

            if (resultCode == RESULT_OK) {

                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

            }*/
        }


        //TODO confirmar que se ha o no se ha modificado
    }
    //si no hay texto escrito devuelve el hint(placeholder)
    private String leerMensaje (EditText mensaje){
        return (mensaje.getText().toString().matches("")? mensaje.getHint().toString() : mensaje.getText().toString());

    }
}

