package com.diaz.inaki.caso_practico_notificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int notifId = 1; //Identificador de la notificación, para futuras modificaciones.
    /* PASO 1: Crear la notificación con sus propiedades */
        NotificationCompat.Builder constructorNotif = new NotificationCompat.Builder(this);
        constructorNotif.setSmallIcon(R.drawable.ic_stat_name);
        constructorNotif.setContentTitle("Mi notificación");
        constructorNotif.setContentText("Has recibido una notificación!!");

        /* PASO 2: Creamos un intent para abrir la actividad cuando se pulse la notificación*/
        Intent resultadoIntent = new Intent(this, MainActivity.class);
        //El objeto stackBuilder crea un back stack que nos asegura que el botón de "Atrás" del
        // dispositivo nos lleva desde la Actividad a la pantalla principal
        TaskStackBuilder pila = TaskStackBuilder.create(this);
        // El padre del stack será la actividad a crear
        pila.addParentStack(MainActivity.class);
        // Añade el Intent que comienza la Actividad al inicio de la pila
        pila.addNextIntent(resultadoIntent);
        PendingIntent resultadoPendingIntent = pila.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        constructorNotif.setContentIntent(resultadoPendingIntent);

          /* PASO 3. Crear y enviar */
        NotificationManager notificador =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificador.notify(notifId, constructorNotif.build());

        /* PASO 4: [Opcional] Crear notificación con layout expandible */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] eventos = new String[5];
        // Título del expanded layout
        inboxStyle.setBigContentTitle("Notificación expandible:");
        eventos[0] = "Esto es la primera línea";
        eventos[1] = "Esto es la segunda línea";
        eventos[2] = "Esto es la tercera línea";
        eventos[3] = "Esto es la cuarta línea";
        eventos[4] = "Esto es la quita línea";
        // Mueve eventos dentro del expanded layout
        for (int i = 0; i < eventos.length; i++) {
            inboxStyle.addLine(eventos[i]);
        }
    /* dar la máxima prioridad y ponerlo en la cima de las notificaciones */
        constructorNotif.setWhen(0);
        constructorNotif.setPriority(Notification.PRIORITY_MAX);
        // Mueve el expanded layout a la notificación.
        constructorNotif.setStyle(inboxStyle);

        notificador =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificador.notify(notifId, constructorNotif.build());
    }
}
