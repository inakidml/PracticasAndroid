package com.diaz.inaki.caso_practico_servicios_hilos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /* Arranca el servicio Wireless Tester */
    public void Arrancar(View v) {
        startService(new Intent(getBaseContext(), WirelessTester.class));
    }

    /* Detiene el servicio Wireless Tester */
    public void Detener(View v) {
        System.out.println("detener pulsado");
        stopService(new Intent(getBaseContext(), WirelessTester.class));

    }
}