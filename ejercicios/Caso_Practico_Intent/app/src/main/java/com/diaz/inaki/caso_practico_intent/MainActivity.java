package com.diaz.inaki.caso_practico_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int SELECCIONA_PROVINCIA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b=(Button)findViewById(R.id.button);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this, selecciona.class);
        startActivityForResult(intent, SELECCIONA_PROVINCIA);
        Log.wtf("log", "texto del log");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        TextView t = (TextView)findViewById(R.id.label);
        if(requestCode == SELECCIONA_PROVINCIA){
            if(resultCode == RESULT_OK){
                t.setText("Se ha seleccionado:\n"+data.getStringExtra("PROVINCIA"));
            }
        }

    }
}
