package com.diaz.inaki.practica3_contactos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VerContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contacto);
        Contacto c = (Contacto) getIntent().getSerializableExtra("Contacto");
        TextView nombre = (TextView)findViewById(R.id.textoNombreVerContacto);
        nombre.setText(c.getName());
        //TODO todos los datos del contacto

    }
}
