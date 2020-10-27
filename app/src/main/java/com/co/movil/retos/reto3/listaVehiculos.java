package com.co.movil.retos.reto3;

import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.retos.R;
import com.co.movil.retos.adapters.AdapterVehiculo;
import com.co.movil.retos.clases.Vehiculo;
import com.co.movil.retos.converter.Converter;
import com.co.movil.retos.persistencia.DataBaseHelper;

import java.util.Comparator;
import java.util.List;

public class listaVehiculos extends AppCompatActivity {

    private DataBaseHelper db;
    public TextView textViewTotalFacturado;
    public ListView listViewVehiculos;
    public List<Vehiculo> vehiculos;
    public AdapterVehiculo adapterVehiculo;
    public Double cantidad;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vehiculos);
        initComponents();
        crearListaVehiculos();
        calcularTotal();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        Converter converter = new Converter();
        vehiculos = converter.convertToVehiculo(db.transaccionesVehiculo().findByValor(1.0));
        listViewVehiculos = findViewById(R.id.listViewVehiculos);
        cantidad = 0.0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void crearListaVehiculos() {
        adapterVehiculo = new AdapterVehiculo(getApplicationContext(), vehiculos);
        listViewVehiculos.setAdapter(adapterVehiculo);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void calcularTotal() {
        textViewTotalFacturado = findViewById(R.id.textViewTotalFacturado);
        vehiculos.forEach(vehiculo -> {
            cantidad = cantidad + vehiculo.getMontoCalculado();
        });
        String cantidadString = getText(R.string.totalParqueadero) + String.valueOf(cantidad);
        textViewTotalFacturado.setText(cantidadString);
    }
}