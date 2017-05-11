package com.diaz.inaki.actividad_11_ficheros_estaticos;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources r = getResources();

        InputStream in = r.openRawResource(R.raw.datos);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        TextView tv=(TextView)findViewById(R.id.TextView);
        String cadenaTemporal;
        tv.setText("");
        try {
        while((cadenaTemporal=br.readLine()) != null)

            tv.setText(tv.getText() + cadenaTemporal + "\n");
        }catch(Exception e){
            System.out.println("Exception");
        }



    }
}
