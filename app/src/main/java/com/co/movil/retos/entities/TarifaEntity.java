package com.co.movil.retos.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.co.movil.retos.persistencia.Tabla;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(tableName = Tabla.TARIFAS)
@NoArgsConstructor
public class TarifaEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idTarifa")
    private Integer idTarifa;

    @ColumnInfo(name = "tipo")
    private String tipoVehiculo;

    @ColumnInfo(name = "valor")
    private Double valor;
}
