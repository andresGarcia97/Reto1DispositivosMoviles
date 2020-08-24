package com.co.movil.reto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void mostrarDerechos(View view){
        Toast.makeText(getApplicationContext(),R.string.derechosAutor,Toast.LENGTH_LONG).show();
    }

    public void lanzarActivityCalcularEdad(View view) {
        Intent intent = new Intent(this, Edad.class);
        startActivity(intent);
    }
}

