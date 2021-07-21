package com.alfredo.frutas.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FrutaCon {
    public SQLiteDatabase db;
    public DBHelper dbHelper;

    public FrutaCon(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void agregarFruta(Fruta fruta){
        ContentValues values = new ContentValues();
        values.put("nombre", fruta.getNombre());
        values.put("color", fruta.getColor());
        values.put("cantidad", fruta.getCantidad());
        values.put("imagen", fruta.getImagen());
        db.insert("FRUTAS", null, values);
    }

    public List<Fruta> obtenerFrutas(){
        List<Fruta> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM FRUTAS", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Integer id_fruta = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String color = cursor.getString(2);
            Integer cantidad = cursor.getInt(3);
            String imagen = cursor.getString(4);
            Fruta fruta = new Fruta(id_fruta, nombre, color, cantidad, imagen);
            lista.add(fruta);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;
    }

    public void actualizarFruta(Fruta fruta){
        ContentValues values = new ContentValues();
        values.put("nombre", fruta.getNombre());
        values.put("color", fruta.getColor());
        values.put("cantidad", fruta.getCantidad());
        values.put("imagen", fruta.getImagen());
        db.update("FRUTAS", values,  " id_fruta = ?", new String[] {String.valueOf(fruta.getId_fruta())});
    }

    public void eliminarFruta(int id){
        db.delete("FRUTAS"," id_fruta = ?", new String[] {String.valueOf(id)});
    }
}
