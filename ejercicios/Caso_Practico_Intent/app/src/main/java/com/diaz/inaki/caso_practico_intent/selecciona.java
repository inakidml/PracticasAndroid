package com.diaz.inaki.caso_practico_intent;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class selecciona extends ListActivity implements ListView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecciona);

        //Crear un array con los elementos seleccionables
        String[] elementos = {"Toledo", "Ciudad Real", "Cuenca", "Guadalajara", "Albacete"};
        //Declarar un adaptador de Texto (String) ArrayAdapter<String> adaptador;
        ArrayAdapter<String> adaptador;
        //Obtenemos una referencia a la lista
        ListView l = (ListView) findViewById(android.R.id.list);
        //Creamos el adaptador
        adaptador = new ArrayAdapter<String>(this, R.layout.fila, elementos);
        //Le damos el adaptador a la lista
        l.setAdapter(adaptador);
        l.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        intent.putExtra("PROVINCIA", adapterView.getItemAtPosition(i).toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
