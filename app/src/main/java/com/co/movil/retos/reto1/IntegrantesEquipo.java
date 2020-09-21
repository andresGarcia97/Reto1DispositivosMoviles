package com.co.movil.retos.reto1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.retos.R;

public class IntegrantesEquipo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrantes_equipo);
    }

    public void mostrarNombreRios(View view) {
        Toast.makeText(getApplicationContext(), R.string.nombreRios, Toast.LENGTH_LONG).show();
    }

    public void mostrarNombreAndres(View view) {
        Toast.makeText(getApplicationContext(), R.string.nombreAndres, Toast.LENGTH_LONG).show();
    }

    public void mostrarNombreDuver(View view) {
        Toast.makeText(getApplicationContext(), R.string.nombreDuver, Toast.LENGTH_LONG).show();
    }
}