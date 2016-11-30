package com.diaz.inaki.practica1;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button miboton;
    EditText editTextPosicion;
    TextView textViewResultado;
    CalculoPrimos cp = new CalculoPrimos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_name);
        miboton = (Button) findViewById(R.id.button);
        editTextPosicion = (EditText) findViewById(R.id.editTextPosicion);
        textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        textViewResultado.setText("");
        miboton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int posicion = Integer.parseInt("0" + editTextPosicion.getText());
        if (posicion > 0 && posicion < 1000000) {
            textViewResultado.setText("El primo número " + posicion + " es: " + cp.devolverPrimo(posicion));
        } else {
            textViewResultado.setText("posición entre 1 y 999999");
        }
    }
}
