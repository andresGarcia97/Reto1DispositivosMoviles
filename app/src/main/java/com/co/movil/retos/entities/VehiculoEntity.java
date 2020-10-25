package com.co.movil.retos.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.co.movil.retos.persistencia.Tabla;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(tableName = Tabla.VEHICULOS)
@NoArgsConstructor
public class VehiculoEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idVehiculo")
    private Integer idVehiculo;

    @ColumnInfo(name = "fechaEntrada")
    private String fechaEntrada;

    @ColumnInfo(name = "idTarifa")
    private Integer idTarifa;

    @ColumnInfo(name = "placa")
    private String placa;

    @ColumnInfo(name = "fechaSalida")
    private String fechaSalida;

    @ColumnInfo(name = "valorPaqueadero")
    private Double valorParqueadero;
}
