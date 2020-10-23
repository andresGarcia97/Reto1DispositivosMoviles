package com.co.movil.retos.clases;

import java.util.Calendar;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Vehiculo {

    private Integer idVehiculo;
    private Calendar fechaEntrada;
    private String placa;
    private Calendar fechaSalida;
    private Double montoCalculado;
}
