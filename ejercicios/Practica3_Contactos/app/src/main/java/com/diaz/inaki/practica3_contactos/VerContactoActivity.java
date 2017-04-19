package com.diaz.inaki.practica3_contactos;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class VerContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contacto);

        Contacto c = (Contacto) getIntent().getSerializableExtra("Contacto");

        ImageView photo = (ImageView)findViewById(R.id.photoVerContacto);
        TextView nombre = (TextView)findViewById(R.id.textoNombreVerContacto);
        TextView telefono = (TextView)findViewById(R.id.textoTelefonoVerContacto);
        CheckBox aviso = (CheckBox)findViewById(R.id.checkBoxVerContacto);
        TextView fechaNacimiento = (TextView)findViewById(R.id.fechaNacimientoVerContacto);
        TextView mensaje = (TextView)findViewById(R.id.mensajeVerContacto);

        photo.setImageURI(Uri.parse(c.getPhotoURI()));
        nombre.setText(c.getName());
        telefono.setText(c.getTelefono());
        fechaNacimiento.setText(c.getFechaNacimiento());
        mensaje.setText(c.getMensaje());
        //TODO aviso

    }
}
