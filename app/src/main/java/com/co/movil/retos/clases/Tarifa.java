package com.co.movil.retos.clases;

import androidx.annotation.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Tarifa {

    private Integer idTarifa;
    private String tipo;
    private Double valor;

    public Tarifa(Integer idTarifa, String tipoVehiculo, Double valor) {
        super();
        this.idTarifa = idTarifa;
        this.tipo = tipoVehiculo;
        this.valor = valor;
    }

    @NonNull
    @Override
    public String toString() {
        return tipo.concat(":").concat(Double.toString(valor));
    }
}
