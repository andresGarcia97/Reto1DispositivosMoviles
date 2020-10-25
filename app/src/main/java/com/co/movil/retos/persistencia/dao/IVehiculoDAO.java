package com.co.movil.retos.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.co.movil.retos.entities.VehiculoEntity;

@Dao
public interface IVehiculoDAO {

    @Query("SELECT * FROM vehiculos Where placa=:placa AND valorPaqueadero=:valor")
    VehiculoEntity findByPLaca(String placa, Double valor);

    @Insert
    void insert(VehiculoEntity vehiculoEntity);

    @Update
    void update(VehiculoEntity vehiculoEntity);
}
