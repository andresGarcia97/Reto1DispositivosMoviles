package com.co.movil.retos.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.co.movil.retos.entities.TarifaEntity;

import java.util.List;

@Dao
public interface ITarifaDAO {

    @Insert
    void insert(TarifaEntity tarifa);

    @Delete
    void delete(TarifaEntity tarifa);

    @Query("DELETE FROM TARIFAS WHERE idTarifa=:idTarifa")
    void deleteByIdTarifa(Integer idTarifa);

    @Query("SELECT * FROM TARIFAS")
    List<TarifaEntity> listar();
}
