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
        TextView tv_gustar = (TextView) findViewById(R.id.textViewGustar);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        nickname = prefs.getString("nickname", "<campo vacío>");
        gustan = prefs.getBoolean("gustanSuperheroes", false);
        Boolean pin = prefs.getBoolean("requiere pin", false);
        String nombre = prefs.getString("Nombre", "");
        String edad = prefs.getString("Edad", "");
        "TargetList"
        "ringtoneAlarm"
        "ringtoneAviso"
                "lista"

        tv_nickname.setText("Nickname: " + nickname);
        tv_gustar.setText("¿Te gustan las preferencias? " + new Boolean(gustan).toString());
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
