package com.alfredo.frutas.model;

import android.content.Context;

import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.datamodel.Fruta;
import com.alfredo.frutas.interfaces.AgregarProductoMVP;
import com.alfredo.frutas.presenter.AgregarProductoPresenter;

public class AgregarProductoModel implements AgregarProductoMVP.Model {
    private static final String TAG = "MODEL ";
    private static AgregarProductoMVP.Presenter presenter;
    private Context context;

    public AgregarProductoModel(AgregarProductoMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void doRegisterImage(String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas) {
        AppDataBase appDataBase = AppDataBase.getInstance(context);

        Fruta fruta = new Fruta();
        fruta.setNombre(nombre);
        fruta.setColor(color);
        fruta.setCantidad(cantidad);
        fruta.setImagen(imagen);
        fruta.setDescripcion(descripcion);
        fruta.setBeneficios(beneficios);
        fruta.setVitaminas(vitaminas);


        appDataBase.frutaDao().insertFruta(fruta);
        presenter.onSuccess("registro exitoso");
    }

    @Override
    public void doRegister(String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas) {
        AppDataBase appDataBase = AppDataBase.getInstance(context);

        Fruta fruta = new Fruta();
        fruta.setNombre(nombre);
        fruta.setColor(color);
        fruta.setCantidad(cantidad);
        fruta.setDescripcion(descripcion);
        fruta.setBeneficios(beneficios);
        fruta.setVitaminas(vitaminas);


        appDataBase.frutaDao().insertFruta(fruta);
        AgregarProductoModel.presenter.onSuccess("registro exitoso");
    }
}
