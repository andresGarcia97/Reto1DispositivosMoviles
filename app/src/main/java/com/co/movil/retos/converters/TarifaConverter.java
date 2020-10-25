package com.co.movil.retos.converters;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.co.movil.retos.clases.Tarifa;
import com.co.movil.retos.entities.TarifaEntity;

import java.util.ArrayList;
import java.util.List;

public class TarifaConverter {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Tarifa> convertTo(List<TarifaEntity> tarifasEntity) {
        final List<Tarifa> tarifas = new ArrayList<>();
        tarifasEntity.forEach(tarifa -> {
            tarifas.add(new Tarifa(tarifa.getIdTarifa(), tarifa.getTipoVehiculo(), tarifa.getValor()));
        });
        return tarifas;
    }

    public Tarifa convertTo(TarifaEntity tarifa){
        return new Tarifa(tarifa.getIdTarifa(), tarifa.getTipoVehiculo(), tarifa.getValor());
    }
}
