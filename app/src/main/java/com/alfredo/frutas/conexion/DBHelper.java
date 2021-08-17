package com.alfredo.frutas.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String BASEDEDATOS =  "frutas";
    public static final int VERSION = 3;

    public DBHelper(Context context){
        super(context, BASEDEDATOS, null, VERSION);
    }

    public static final String TABLA_FRUTAS = "CREATE TABLE FRUTAS "
            + "( id_fruta INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " nombre TEXT, "
            + " color TEXT, "
            + " cantidad TEXT, "
            + " imagen TEXT DEFAULT 'null' "
            +");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USUARIO (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT UNIQUE, contraseña TEXT);");
        db.execSQL("CREATE TABLE FRUTAS ( id_fruta INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, color TEXT, cantidad TEXT, imagen TEXT DEFAULT 'null', descripcion TEXT, beneficios TEXT, vitaminas TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USUARIO");
        db.execSQL("DROP TABLE IF EXISTS FRUTAS");
    }
}
