package com.co.movil.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class IntegrantesEquipo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrantes_equipo);
    }

    public void mostrarNombreRios(View view) {
        Toast.makeText(getApplicationContext(),"Santiago Rios Rios",Toast.LENGTH_LONG).show();
    }
}