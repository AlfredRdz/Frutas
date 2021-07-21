package com.alfredo.frutas.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioCon {
    public SQLiteDatabase db;
    public DBHelper dbHelper;

    public UsuarioCon(Context context){
        dbHelper =new DBHelper(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void agregarUsuario(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("usuario", usuario.getUsuario());
        values.put("contraseña", usuario.getContraseña());
        db.insert("USUARIO", null, values);

    }

    public boolean login(String nombre, String contraseña){
        Cursor cursor = db.rawQuery("select * from USUARIO WHERE usuario = ? AND contraseña = ?", new String[] {nombre, contraseña});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
}
