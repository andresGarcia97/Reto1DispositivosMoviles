package com.co.movil.retos.reto3;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class listaVehiculos extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private DataBaseHelper db;
    public TextView textViewTotalFacturado;
    public ListView listViewVehiculos;
    public List<Vehiculo> vehiculos;
    public AdapterVehiculo adapterVehiculo;
    public Double cantidad;
    private Calendar fechaInicial = null;
    private Calendar fechaActual = null;
    public DatePickerDialog datePickerDialog;
    private String dateSelected = "";
    public Button buttonSeleccionFecha;

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
        buttonSeleccionFecha = findViewById(R.id.buttonSeleccionFecha);
        fechaActual = Calendar.getInstance();
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
        fechaInicial = Calendar.getInstance();
        fechaInicial.set(Calendar.YEAR, year);
        fechaInicial.set(Calendar.MONTH, month);
        fechaInicial.set(Calendar.DAY_OF_MONTH, day);
        dateSelected = "Desde: " + year + "/" + (month + 1) + "/" + day + " Hasta: " +
                fechaActual.get(Calendar.YEAR) + "/" + (fechaActual.get(Calendar.MONTH) + 1) + "/" + fechaActual.get(Calendar.DAY_OF_MONTH);
    }

    public void seleccionarFechaInicial(View view) {
        showDatePicker();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filtrarPorFechas(View view) {
        if (fechaInicial == null || dateSelected.isEmpty()) {
            Toast.makeText(getApplication(), R.string.noSeleccionoFecha, Toast.LENGTH_SHORT).show();
        }
        else if(fechaInicial.after(fechaActual)){
            Toast.makeText(getApplication(), R.string.fechaInvalida, Toast.LENGTH_SHORT).show();
        }
        else {
            buttonSeleccionFecha.setText(dateSelected);
            Date dateInicial = fechaInicial.getTime();

            this.vehiculos = this.vehiculos.stream()
                    .filter(vehiculo -> vehiculo.getFechaSalida().after(dateInicial))
                    .collect(Collectors.toList());

            crearListaVehiculos();
            calcularTotal();
        }
    }
}