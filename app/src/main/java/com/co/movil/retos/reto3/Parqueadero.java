package com.co.movil.retos.reto3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.retos.R;

public class Parqueadero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parqueadero);
    }

    public void lanzarActivityIngresoTarifas(View view) {
        Intent intent = new Intent(this, Tarifas.class);
        startActivity(intent);
    }

    public void lanzarActivityIngresoVehiculo(View view) {
        Intent intent = new Intent(this, IngresoVehiculo.class);
        startActivity(intent);
    }

    public void lanzarActivityVehiculos(View view) {
        Intent intent = new Intent(this, listaVehiculos.class);
        startActivity(intent);
    }
}