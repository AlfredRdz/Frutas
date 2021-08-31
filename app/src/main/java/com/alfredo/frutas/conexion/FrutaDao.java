package com.alfredo.frutas.conexion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FrutaDao {
    @Query("SELECT * FROM fruta")
    List<Fruta> getAllFrutas();

    @Insert
    void insertFruta(Fruta... fruta);

    @Update
    void actualizarFruta(Fruta... fruta);

    @Delete
    void deleteFruta(Fruta fruta);
}
