package com.co.movil.retos.clases;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Vehiculo {

    private Integer idVehiculo;
    private Date fechaEntrada;
    private String placa;
    private Date fechaSalida;
    private Double montoCalculado;

    public Vehiculo(Integer idVehiculo, Date fechaEntrada, String placa, Date fechaSalida, Double montoCalculado) {
        super();
        this.idVehiculo = idVehiculo;
        this.fechaEntrada = fechaEntrada;
        this.placa = placa;
        this.fechaSalida = fechaSalida;
        this.montoCalculado = montoCalculado;
    }
}
