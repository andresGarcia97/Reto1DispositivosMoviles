package com.co.movil.retos.reto3;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.retos.R;
import com.co.movil.retos.adapters.AdapterVehiculo;
import com.co.movil.retos.clases.Vehiculo;
import com.co.movil.retos.converter.Converter;
import com.co.movil.retos.persistencia.DataBaseHelper;

import java.util.Calendar;
import java.util.List;

public class listaVehiculos extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private DataBaseHelper db;
    public TextView textViewTotalFacturado;
    public ListView listViewVehiculos;
    public List<Vehiculo> vehiculos;
    public AdapterVehiculo adapterVehiculo;
    public Double cantidad;
    private Calendar fechaInicial = null;
    private Calendar fechaFinal = null;
    private Calendar fechaSeleccionada = null;
    public DatePickerDialog datePickerDialog;
    private String dateSelected;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vehiculos);
        initComponents();
        crearListaVehiculos();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        Converter converter = new Converter();
        vehiculos = converter.convertToVehiculo(db.transaccionesVehiculo().findByValor(1.0));
        listViewVehiculos = findViewById(R.id.listViewVehiculos);
        cantidad = 0.0;
        fechaInicial = Calendar.getInstance();
        fechaFinal = Calendar.getInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void crearListaVehiculos() {
        adapterVehiculo = new AdapterVehiculo(getApplicationContext(), vehiculos);
        listViewVehiculos.setAdapter(adapterVehiculo);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void calcularTotal() {
        textViewTotalFacturado = findViewById(R.id.textViewTotalFacturado);
        String cantidadString = "0";
        cantidad = 0.0;
        if (vehiculos.isEmpty()) {
            Toast.makeText(getApplication(), R.string.sinVehiculos, Toast.LENGTH_SHORT).show();
        } else {
            vehiculos.forEach(vehiculo -> {
                cantidad = cantidad + vehiculo.getMontoCalculado();
            });
            cantidadString = getText(R.string.totalParqueadero) + String.valueOf(cantidad);
        }
        textViewTotalFacturado.setText(cantidadString);
    }

    private void showDatePicker() {
        Calendar minimeYear = Calendar.getInstance();
        Calendar maximeYear = Calendar.getInstance();
        Calendar currentlyDate = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, this,
                currentlyDate.get(Calendar.YEAR), currentlyDate.get(Calendar.MONTH), currentlyDate.get(Calendar.DAY_OF_MONTH));
        minimeYear.set(Calendar.YEAR, currentlyDate.get(Calendar.YEAR) - 1);
        datePickerDialog.getDatePicker().setMinDate(minimeYear.getTimeInMillis());
        maximeYear.set(Calendar.YEAR, currentlyDate.get(Calendar.YEAR) + 1);
        datePickerDialog.getDatePicker().setMaxDate(maximeYear.getTimeInMillis());
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        fechaSeleccionada = Calendar.getInstance();
        fechaSeleccionada.set(Calendar.YEAR, year);
        fechaSeleccionada.set(Calendar.MONTH, month);
        fechaSeleccionada.set(Calendar.DAY_OF_MONTH, day);
        dateSelected = "Fecha: " + year + " / " + (month + 1) + " / " + day;
        Toast.makeText(getApplication(), dateSelected, Toast.LENGTH_SHORT).show();
        datePickerDialog.dismiss();
    }

    public void seleccionarFechaFinal(View view) {
        showDatePicker();
        fechaFinal = fechaSeleccionada;
    }

    public void seleccionarFechaInicial(View view) {
        showDatePicker();
        fechaInicial = fechaSeleccionada;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filtrarPorFechas(View view) {
        // insertar codigo para filtrar fechas
        crearListaVehiculos();
        calcularTotal();
    }
}