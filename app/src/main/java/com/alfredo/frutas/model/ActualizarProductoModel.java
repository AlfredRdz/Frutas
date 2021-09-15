package com.alfredo.frutas.model;

import android.content.Context;

import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.datamodel.Fruta;
import com.alfredo.frutas.interfaces.ActualizarProductoMVP;
import com.alfredo.frutas.presenter.ActualizarProductoPresenter;

public class ActualizarProductoModel implements ActualizarProductoMVP.Model {
    private static final String TAG = "MODEL";
    private static ActualizarProductoMVP.Presenter presenter;
    private Context context;

    public ActualizarProductoModel(ActualizarProductoMVP.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void doUpdateImage(Integer id, String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas) {
        Fruta fruta = new Fruta();
        fruta.setId_fruta(id);
        fruta.setNombre(nombre);
        fruta.setColor(color);
        fruta.setCantidad(cantidad);
        fruta.setImagen(imagen);
        fruta.setDescripcion(descripcion);
        fruta.setBeneficios(beneficios);
        fruta.setVitaminas(vitaminas);

        AppDataBase appDataBase = AppDataBase.getInstance(context);
        appDataBase.frutaDao().actualizarFruta(fruta);
        ActualizarProductoModel.presenter.onSuccess("Fruta Actualizada");
    }

    @Override
    public void doUpdate(Integer id, String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas) {
        Fruta fruta = new Fruta();
        fruta.setId_fruta(id);
        fruta.setNombre(nombre);
        fruta.setColor(color);
        fruta.setCantidad(cantidad);
        fruta.setDescripcion(descripcion);
        fruta.setBeneficios(beneficios);
        fruta.setVitaminas(vitaminas);

        AppDataBase appDataBase = AppDataBase.getInstance(context);
        appDataBase.frutaDao().actualizarFruta(fruta);
        ActualizarProductoModel.presenter.onSuccess("Fruta Actualizada");
    }
}
