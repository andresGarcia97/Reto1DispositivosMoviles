package com.co.movil.retos.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.co.movil.retos.entities.VehiculoEntity;

import java.util.List;

@Dao
public interface IVehiculoDAO {

    @Query("SELECT * FROM vehiculos Where placa=:placa AND valorPaqueadero = 0.0")
    VehiculoEntity findByPLaca(String placa);

    @Query("SELECT * FROM vehiculos Where valorPaqueadero>=:valor")
    List<VehiculoEntity> findByValor(Double valor);

    @Insert
    void insert(VehiculoEntity vehiculoEntity);

}
