package com.diaz.inaki.practica3_contactos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class VerContactoActivity extends AppCompatActivity implements View.OnClickListener {
    private Contacto c;
    private CheckBox aviso;
    private TextView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contacto);
        Button botonGuardar = (Button) findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(this);

        c = (Contacto) getIntent().getSerializableExtra("Contacto");

        ImageView photo = (ImageView) findViewById(R.id.photoVerContacto);
        TextView nombre = (TextView) findViewById(R.id.textoNombreVerContacto);
        TextView telefono = (TextView) findViewById(R.id.textoTelefonoVerContacto);
        aviso = (CheckBox) findViewById(R.id.checkBoxVerContacto);
        TextView fechaNacimiento = (TextView) findViewById(R.id.fechaNacimientoVerContacto);
        mensaje = (TextView) findViewById(R.id.mensajeVerContacto);

        photo.setImageURI(Uri.parse(c.getPhotoURI()));
        nombre.setText(c.getName());
        telefono.setText(c.getTelefono());
        fechaNacimiento.setText(c.getFechaNacimiento());
        mensaje.setText(c.getMensaje());
        //TODO aviso, mensaje

    }

    @Override
    public void onClick(View view) {
        //TODO guardar
        //TODO abrir contactos
        Intent i=new Intent();
        i.putExtra("Contacto",c);
        setResult(RESULT_OK,i);
        finish();
    }
}
