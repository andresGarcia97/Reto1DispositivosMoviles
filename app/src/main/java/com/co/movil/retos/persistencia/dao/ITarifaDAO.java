package com.co.movil.retos.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.co.movil.retos.entities.TarifaEntity;

import java.util.List;

@Dao
public interface ITarifaDAO {

    @Insert
    void insert(TarifaEntity tarifa);

    @Query("SELECT * FROM TARIFAS WHERE idTarifa=:idTarifa")
    TarifaEntity selectByIdTarifa(Integer idTarifa);

    @Query("SELECT * FROM TARIFAS")
    List<TarifaEntity> listar();
}
