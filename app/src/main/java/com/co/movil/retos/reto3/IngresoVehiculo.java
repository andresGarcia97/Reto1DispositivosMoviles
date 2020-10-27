package com.co.movil.retos.reto3;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.co.movil.retos.R;
import com.co.movil.retos.clases.Tarifa;
import com.co.movil.retos.converter.Converter;
import com.co.movil.retos.entities.VehiculoEntity;
import com.co.movil.retos.persistencia.DataBaseHelper;
import com.co.movil.retos.util.DateUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class IngresoVehiculo extends AppCompatActivity {

    private DataBaseHelper db;
    public EditText editTextTextIngresoPlaca;
    public TextView textViewTotalCobro;
    public TextView textViewHorasTotal;
    public Spinner tipoTarifaSpinner;
    public Button buttonIngresar;
    public Button buttonSalida;
    public ConstraintLayout layoutDatos;
    private List<Tarifa> listaTarifas;
    private VehiculoEntity vehiculoIngresar;
    private VehiculoEntity vehiculoSacar;
    private Tarifa tarifa;
    private Tarifa tarifaSalida;
    private String[] arrayTarifas;
    private Double monto = 0.0;
    private String fechaSalida = "";

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_vehiculo);
        initComponents();
        cargarSpinner();
        hideComponents();
        spinnerOnItemSelected();
    }

    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        editTextTextIngresoPlaca = findViewById(R.id.editTextTextIngresoPlaca);
        tipoTarifaSpinner = findViewById(R.id.tipoTarifaSpinner);
        buttonIngresar = findViewById(R.id.buttonIngresar);
        buttonSalida = findViewById(R.id.buttonSalida);
        layoutDatos = findViewById(R.id.layoutDatos);
    }

    private void hideComponents() {
        tipoTarifaSpinner.setVisibility(View.GONE);
        buttonIngresar.setVisibility(View.GONE);
        buttonSalida.setVisibility(View.GONE);
        layoutDatos.setVisibility(View.GONE);
        editTextTextIngresoPlaca.setText("");
    }

    private void spinnerOnItemSelected() {
        tipoTarifaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tarifa = listaTarifas.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplication(), R.string.seleccionarTarifa, Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void showComponentesSalida() {
        textViewHorasTotal = findViewById(R.id.textViewHorasTotal);
        textViewTotalCobro = findViewById(R.id.textViewTotalCobro);
        buttonSalida.setVisibility(View.VISIBLE);
        layoutDatos.setVisibility(View.VISIBLE);
    }

    private void showInfoDataLayout() {
        fechaSalida = DateUtil.convertDateToString(new Date());
        Converter converter = new Converter();
        tarifaSalida = converter.convertTo(db.transaccionesTarifa().selectByIdTarifa(vehiculoIngresar.getIdTarifa()));
        int horas = 0;
        try {
            horas = DateUtil.timeFromDates(vehiculoIngresar.getFechaEntrada(), fechaSalida);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (horas <= 0) {
            horas = 1;
        }
        monto = calcularMonto(horas, tarifaSalida.getValor());
        textViewTotalCobro.setText(String.valueOf(monto));
        textViewHorasTotal.setText(String.valueOf(horas));
    }

    private void showComponentesIngreso() {
        tipoTarifaSpinner.setVisibility(View.VISIBLE);
        buttonIngresar.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void cargarSpinner() {
        Converter converter = new Converter();
        listaTarifas = converter.convertTo(db.transaccionesTarifa().listar());
        if (listaTarifas.isEmpty()) {
            Toast.makeText(getApplication(), R.string.sinTarifas, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            arrayTarifas = new String[listaTarifas.size()];
            for (int i = 0; i < listaTarifas.size(); i++) {
                arrayTarifas[i] = listaTarifas.get(i).toString();
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayTarifas);
            tipoTarifaSpinner.setAdapter(arrayAdapter);
        }
    }

    public void buscarVehiculo(View view) {
        vehiculoIngresar = db.transaccionesVehiculo().findByPLaca(editTextTextIngresoPlaca.getText().toString());
        if (vehiculoIngresar == null) {
            showComponentesIngreso();
        } else {
            showComponentesSalida();
            showInfoDataLayout();
        }
    }

    public void registrarVehiculo(View view) {
        if (tarifa == null) {
            Toast.makeText(getApplicationContext(), R.string.seleccionarTarifa, Toast.LENGTH_SHORT).show();
        } else if (vehiculoIngresar == null) {
            vehiculoIngresar = new VehiculoEntity();
            vehiculoIngresar.setPlaca(editTextTextIngresoPlaca.getText().toString());
            vehiculoIngresar.setIdTarifa(tarifa.getIdTarifa());
            vehiculoIngresar.setFechaEntrada(DateUtil.convertDateToString(new Date()));
            vehiculoIngresar.setValorParqueadero(0.0);
            new InsercionVehiculo().execute(vehiculoIngresar);
            vehiculoIngresar = null;
            hideComponents();
        }
    }

    public void registrarSalida(View view) {
        if (vehiculoIngresar == null) {
            Toast.makeText(getApplicationContext(), R.string.busqueVehiculo, Toast.LENGTH_SHORT).show();
        } else {
            vehiculoSacar = new VehiculoEntity();
            vehiculoSacar.setPlaca(vehiculoIngresar.getPlaca());
            vehiculoSacar.setIdTarifa(vehiculoIngresar.getIdTarifa());
            vehiculoSacar.setFechaEntrada(vehiculoIngresar.getFechaEntrada());
            vehiculoSacar.setFechaSalida(fechaSalida);
            vehiculoSacar.setValorParqueadero(monto);
            new SalidaVehiculo().execute(vehiculoSacar);
            vehiculoIngresar = null;
            vehiculoSacar = null;
            tarifa = null;
            hideComponents();
        }
    }

    private Double calcularMonto(Integer horas, Double valorTarifa) {
        return horas * valorTarifa;
    }

    private class InsercionVehiculo extends AsyncTask<VehiculoEntity, Void, Void> {

        @Override
        protected Void doInBackground(VehiculoEntity... vehiculoEntities) {
            DataBaseHelper.getSimpleDB(getApplicationContext()).transaccionesVehiculo().insert(vehiculoEntities[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), R.string.registroExitoso, Toast.LENGTH_SHORT).show();
        }
    }

    private class SalidaVehiculo extends AsyncTask<VehiculoEntity, Void, Void> {

        @Override
        protected Void doInBackground(VehiculoEntity... vehiculoEntities) {
            DataBaseHelper.getSimpleDB(getApplicationContext()).transaccionesVehiculo().insert(vehiculoEntities[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), R.string.salidaRegistrada, Toast.LENGTH_SHORT).show();
        }
    }
}