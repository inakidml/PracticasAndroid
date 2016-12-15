package com.diaz.inaki.ejemploradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, OnClickListener {

    TextView etiqueta;
    RadioGroup radioGroup;
    CheckBox checkBox;
    Button boton;
    String cadena="";
    String aniadido = "\n y ademas te interesa este tema mucho";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioButtonGroup);
        etiqueta = (TextView) findViewById(R.id.textViewResp);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        boton=(Button)findViewById(R.id.button);
        boton.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        checkBox.setOnClickListener(this);
        etiqueta.setText("");

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radioButton:
                cadena = "Que bien se está soltero";
                etiqueta.setText(cadena);
                if (checkBox.isChecked()) {
                    etiqueta.setText(cadena + aniadido);
                }
                break;
            case R.id.radioButton2:
                cadena = "Que bien se está casado";
                etiqueta.setText(cadena);
                if (checkBox.isChecked()) {
                    etiqueta.setText(cadena + aniadido);
                }
                break;
            case R.id.radioButton3:
                cadena = "Que bien se está viudo";
                etiqueta.setText(cadena);
                if (checkBox.isChecked()) {
                    etiqueta.setText(cadena + aniadido);
                }
                break;
            case R.id.radioButton4:
                cadena = "Que bien se está divorciado";
                etiqueta.setText(cadena);
                if (checkBox.isChecked()) {
                    etiqueta.setText(cadena + aniadido);
                }
                break;
            default:
                etiqueta.setText("ninguno");

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkBox:
                if (checkBox.isChecked()) {
                    etiqueta.setText(cadena + aniadido);
                } else {
                    etiqueta.setText(cadena);
                }
                break;
            case R.id.button:
                cadena="";
                radioGroup.clearCheck();
                etiqueta.setText("");
                checkBox.setChecked(false);
                break;
        }
    }
}

