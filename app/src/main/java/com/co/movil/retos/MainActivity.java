package com.co.movil.retos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.retos.reto1.Edad;
import com.co.movil.retos.reto1.FormularioIncripcion;
import com.co.movil.retos.reto1.IntegrantesEquipo;
import com.co.movil.retos.reto2.Animales;
import com.co.movil.retos.reto3.Parqueadero;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mostrarDerechos(View view) {
        Toast.makeText(getApplicationContext(), R.string.derechosAutor, Toast.LENGTH_SHORT).show();
    }

    public void lanzarActivityCalcularEdad(View view) {
        Intent intent = new Intent(this, Edad.class);
        startActivity(intent);
    }

    public void lanzarActivityIntegrantes(View view) {
        Intent intent = new Intent(this, IntegrantesEquipo.class);
        startActivity(intent);
    }

    public void lanzarActivityFormulario(View view) {
        Intent intent = new Intent(this, FormularioIncripcion.class);
        startActivity(intent);
    }

    public void lanzarActivityAnimales(View view) {
        Intent intent = new Intent(this, Animales.class);
        startActivity(intent);
    }

    public void lanzarActivityParqueadero(View view) {
        Intent intent = new Intent(this, Parqueadero.class);
        startActivity(intent);
    }
}

