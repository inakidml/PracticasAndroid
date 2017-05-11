package com.diaz.inaki.actividad_9_preference_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void editarPreferencias(View view){
        startActivity(new Intent(this, MisPreferencias.class));
    }

    public void onResume(){
        super.onResume();
        String nickname;
        boolean gustan;

        TextView tv_nickname = (TextView) findViewById(R.id.textViewNombre);
        TextView tv_gustar = (TextView) findViewById(R.id.textViewGustan);
        TextView tv_pin = (TextView) findViewById(R.id.tvPin);
        TextView tv_nombre = (TextView) findViewById(R.id.tvNombre);
        TextView tv_edad = (TextView) findViewById(R.id.tvEdad) ;
        TextView tv_opcion = (TextView ) findViewById(R.id.tvOpcion);
        TextView tv_alarma = (TextView ) findViewById(R.id.textAlarma);
        TextView tv_aviso = (TextView ) findViewById(R.id.textAviso);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        nickname = prefs.getString("nickname", "<campo vacío>");
        gustan = prefs.getBoolean("gustanPreferencias", false);
        Boolean pin = prefs.getBoolean("requiere pin", false);
        String nombre = prefs.getString("Nombre", "");
        String edad = prefs.getString("Edad", "");
        String opcion = prefs.getString("lista", "no seleccionado");
        String ringTone = prefs.getString("ringtoneAlarm", "vacio");
        String aviso = prefs.getString("ringtoneAviso", "vacío");



        tv_nickname.setText("Nickname: " + nickname);
        tv_gustar.setText("¿Te gustan las preferencias?: " + new Boolean(gustan).toString());
        tv_pin.setText("¿Requiere pin?: " + new Boolean((pin).toString()));
        tv_nombre.setText("Nombre: " +nombre);
        tv_edad.setText("Edad: " + edad);
        tv_opcion.setText("Video mascota: " + opcion);
        tv_alarma.setText("Alarma: " + ringTone);
        tv_aviso.setText("Aviso: " + aviso);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
