package com.co.movil.retos.persistencia;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.co.movil.retos.entities.TarifaEntity;
import com.co.movil.retos.entities.VehiculoEntity;
import com.co.movil.retos.persistencia.dao.ITarifaDAO;
import com.co.movil.retos.persistencia.dao.IVehiculoDAO;

// entidades

@Database(entities = {
        VehiculoEntity.class, TarifaEntity.class}, version = DataBaseHelper.VERSION_BASE_DATOS, exportSchema = false)

public abstract class DataBaseHelper extends RoomDatabase {

    public static final int VERSION_BASE_DATOS = 1;
    public static final String NOMBRE_BASE_DATOS = "retos";
    private static DataBaseHelper INSTANCIA;


    public static DataBaseHelper getSimpleDB(Context context) {
        if (INSTANCIA == null) {
            INSTANCIA = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).build();
        }
        return INSTANCIA;
    }

    public static DataBaseHelper getDBMainThread(Context context) {
        if (INSTANCIA == null) {
            INSTANCIA = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCIA;
    }

    // DAOs

    public abstract IVehiculoDAO transaccionesVehiculo();

    public abstract ITarifaDAO transaccionesTarifa();
}
