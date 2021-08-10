package com.alfredo.frutas.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

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
        //long id = 0;

        ContentValues values = new ContentValues();
        values.put("usuario", usuario.getUsuario());
        values.put("contraseña", usuario.getContraseña());
        db.insert("USUARIO", null, values);

//        try {
//
//            boolean comprobar = login(usuario.getUsuario(), usuario.getContraseña());
//
//            if (comprobar == false) {
//                id = db.insert("USUARIO", null, values);
//            }
//
//        } catch (Exception e){
//            e.toString();
//        }

        //return id;
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
