package com.co.movil.retos.reto3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.retos.R;
import com.co.movil.retos.entities.TarifaEntity;
import com.co.movil.retos.persistencia.DataBaseHelper;

public class Tarifas extends AppCompatActivity {

    protected EditText editTextTextTipoTarifa;
    protected EditText editTextNumberDecimalTarifa;
    private DataBaseHelper db;
    private String tipo;
    private String valor;
    private Double valorDouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifas);
        initComponents();
    }

    public void initComponents() {
        editTextNumberDecimalTarifa = findViewById(R.id.editTextNumberDecimalTarifa);
        editTextTextTipoTarifa = findViewById(R.id.editTextTextTipoTarifa);
    }

    private TarifaEntity comprobarDatos() {
        tipo = editTextTextTipoTarifa.getText().toString();
        valor = editTextNumberDecimalTarifa.getText().toString();

        if ("".equals(tipo)) {
            editTextTextTipoTarifa.setError(getString(R.string.campoRequerido));
            return null;
        }
        if ("".equals(valor)) {
            editTextNumberDecimalTarifa.setError(getString(R.string.campoRequerido));
            return null;
        } else if (Double.parseDouble(editTextNumberDecimalTarifa.getText().toString()) == 0) {
            editTextNumberDecimalTarifa.setError(getString(R.string.campoMayorQueCero));
            return null;
        } else {
            TarifaEntity tarifa = new TarifaEntity();
            tarifa.setTipoVehiculo(tipo);
            tarifa.setValor(Double.parseDouble(valor));
            return tarifa;
        }
    }

    public void guardarTarifa(View view) {
        TarifaEntity tarifaInsert = comprobarDatos();
        if (tarifaInsert != null) {
            new RepositoryTarifa().execute(tarifaInsert);
        }
    }

    private class RepositoryTarifa extends AsyncTask<TarifaEntity, Void, Void> {

        @Override
        protected Void doInBackground(TarifaEntity... tarifaEntities) {
            DataBaseHelper.getSimpleDB(getApplicationContext()).transaccionesTarifa().insert(tarifaEntities[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), getString(R.string.insercionTarifa), Toast.LENGTH_SHORT).show();
        }
    }
}