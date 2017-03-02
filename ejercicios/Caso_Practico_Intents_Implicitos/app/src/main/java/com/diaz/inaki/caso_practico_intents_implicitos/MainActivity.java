package com.diaz.inaki.caso_practico_intents_implicitos;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        String ac = i.getAction();
        String textRecibido;

        //Si el tipo de comando es ACTION_SEND
        if (ac.equals(Intent.ACTION_SEND)) {
            EditText edRecibido = (EditText) findViewById(R.id.edRecibido);
            //Obtenemos la información que nos han compartido
            textRecibido = i.getStringExtra(Intent.EXTRA_TEXT);
            if (textRecibido != null)
                //Actualizamos la caja de texto
                edRecibido.setText(textRecibido);
        }
    }

    public void abrir(View v) {
        Intent i = new Intent();
        Intent chooser = null;
        switch (v.getId()) {
            case R.id.btnWeb:
                EditText edURL = (EditText) findViewById(R.id.edURL);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(edURL.getText().toString()));
                chooser = i.createChooser(i, "Elige un navegador");
                startActivity(chooser);
                Toast.makeText(this.getApplicationContext(), "Acceso a web!", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnMapa:
                EditText edLongitud = (EditText) findViewById(R.id.edLongitud);
                EditText edLatitud = (EditText) findViewById(R.id.edLatitud);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:" + edLatitud.getText().toString() + "," + edLongitud.getText().toString()));
                chooser = i.createChooser(i, "Lanzar Maps");
                startActivity(chooser);
                Toast.makeText(this.getApplicationContext(), "Acceso a mapas!", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnEnviar:
                EditText edEmail = (EditText) findViewById(R.id.edEmail);
                i.setAction(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                String para[] = {edEmail.getText().toString(), "kkk@lll.es", "otro@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL, para);
                i.putExtra(Intent.EXTRA_SUBJECT, "Saludos desde Android");
                i.putExtra(Intent.EXTRA_TEXT, "Hola!. Este es nuestro primer email!!");
                i.setType("message/rfc822");
                chooser = i.createChooser(i, "Enviar email");
                startActivity(chooser);
                Toast.makeText(this.getApplicationContext(), "Envía el email!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnCam:
                i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                Toast.makeText(this.getApplicationContext(), "Acceso a cámara!", Toast.LENGTH_LONG).show();
                chooser = i.createChooser(i, "Lanzar Cámara");
                startActivity(chooser);
                break;
        }

    }
}
