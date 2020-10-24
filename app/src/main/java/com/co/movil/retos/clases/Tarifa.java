package com.co.movil.retos.clases;

import androidx.annotation.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Tarifa {

    private String tipo;
    private Double valor;

    public Tarifa(String tipoVehiculo, Double valor) {
        super();
        this.tipo = tipoVehiculo;
        this.valor = valor;
    }

    @NonNull
    @Override
    public String toString() {
        return "Tipo de Vehiculo: ".concat(tipo).concat("  Valor: ").concat(Double.toString(valor));
    }
}
