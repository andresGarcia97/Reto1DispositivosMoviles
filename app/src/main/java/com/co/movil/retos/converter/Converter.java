package com.co.movil.retos.converter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.co.movil.retos.clases.Tarifa;
import com.co.movil.retos.clases.Vehiculo;
import com.co.movil.retos.entities.TarifaEntity;
import com.co.movil.retos.entities.VehiculoEntity;
import com.co.movil.retos.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Tarifa> convertTo(List<TarifaEntity> tarifasEntity) {
        final List<Tarifa> tarifas = new ArrayList<>();
        tarifasEntity.forEach(tarifa -> {
            tarifas.add(new Tarifa(tarifa.getIdTarifa(), tarifa.getTipoVehiculo(), tarifa.getValor()));
        });
        return tarifas;
    }

    public Tarifa convertTo(TarifaEntity tarifa) {
        return new Tarifa(tarifa.getIdTarifa(), tarifa.getTipoVehiculo(), tarifa.getValor());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Vehiculo> convertToVehiculo(List<VehiculoEntity> vehiculosEntity) {
        final List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculosEntity.forEach(vehiculo -> {
            vehiculos.add(new Vehiculo(vehiculo.getIdVehiculo(), DateUtil.convertStringToDate(vehiculo.getFechaEntrada()), vehiculo.getPlaca(),
                    DateUtil.convertStringToDate(vehiculo.getFechaSalida()), vehiculo.getValorParqueadero()));
        });
        return vehiculos;
    }
}
