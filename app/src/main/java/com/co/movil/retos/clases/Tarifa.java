package com.co.movil.retos.clases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Tarifa {

    private Integer idTarifa;
    private String tipoVehiculo;
    private Double valor;
}
