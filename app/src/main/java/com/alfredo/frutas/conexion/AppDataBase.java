package com.alfredo.frutas.conexion;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.UUID;

@Database(entities = {Fruta.class, Usuario.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract FrutaDao frutaDao();

    public abstract UsuarioDao usuarioDao();

    private static AppDataBase INSTANCE;

    public static AppDataBase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "FrutasDB")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
