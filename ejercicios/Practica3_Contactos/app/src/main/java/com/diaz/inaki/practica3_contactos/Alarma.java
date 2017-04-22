package com.diaz.inaki.practica3_contactos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by inaki on 22/4/17.
 */

public class Alarma extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("Alarma disparada!!!");
            CharSequence text = "Alarma disparada!!!!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

