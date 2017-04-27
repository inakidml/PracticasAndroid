package com.diaz.inaki.actividad_8_persistencia_shared_preferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botonLeer = (Button) findViewById(R.id.buttonLeerPref);
        botonLeer.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EditText nombre = (EditText)findViewById(R.id.editTextName);
        EditText apellido = (EditText)findViewById(R.id.editTextSurName);
        EditText edad = (EditText)findViewById(R.id.editTextAge);

        // Se crea el objeto de la clase SharedPreferences
        SharedPreferences misPreferencias = getSharedPreferences("prefs", MODE_PRIVATE);
        // Se crea el objeto editor que permite modificar los valores del objeto misPreferencias
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("nombre", nombre.getText().toString());
        editor.putString("apellidos",apellido.getText().toString());
        editor.putString("edad",edad.getText().toString());
        editor.commit();


    }

    @Override
    public void onClick(View view) {
        SharedPreferences misPreferencias = getSharedPreferences("prefs", MODE_PRIVATE);
        String nombre = misPreferencias.getString("nombre", "en blanco");
        String apellidos = misPreferencias.getString("apellidos", "en blanco");
        String edad = misPreferencias.getString("edad", "0");

        TextView etiquetaNombre = (TextView)findViewById(R.id.textViewNombre);
        TextView etiquetaApellido = (TextView)findViewById(R.id.textViewApellido);
        TextView etiquetaEdad = (TextView)findViewById(R.id.textViewEdad);
        etiquetaNombre.setText(nombre);
        etiquetaApellido.setText(apellidos);
        etiquetaEdad.setText(edad);


    }
}
