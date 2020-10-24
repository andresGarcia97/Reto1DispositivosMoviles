package com.co.movil.retos.reto3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.retos.R;
import com.co.movil.retos.clases.Tarifa;
import com.co.movil.retos.converters.TarifaConverter;
import com.co.movil.retos.persistencia.DataBaseHelper;

import java.util.List;

public class listaTarifas extends AppCompatActivity {

    private DataBaseHelper db;
    public ListView listViewTarifas;
    public List<Tarifa> listaTarifas;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarifas);
        initComponents();
        cargarTarifas();
    }

    public void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        listViewTarifas = findViewById(R.id.listViewTarifas);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void cargarTarifas() {
        TarifaConverter converter = new TarifaConverter();
        listaTarifas = converter.convertTo(db.transaccionesTarifa().listar());
        if (listaTarifas.isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.sinTarifas), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Tarifas.class);
            startActivity(intent);
        }
        else{
            String [] tarifas = new String[listaTarifas.size()];
            for (int i = 0; i < tarifas.length; i++) {
                tarifas[i] = listaTarifas.get(i).toString();
            }
            ArrayAdapter<String> tarifasAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, tarifas);
            listViewTarifas.setAdapter(tarifasAdapter);
        }
    }

}