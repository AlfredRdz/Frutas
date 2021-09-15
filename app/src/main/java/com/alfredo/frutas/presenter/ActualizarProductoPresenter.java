package com.alfredo.frutas.presenter;

import android.content.Context;

import com.alfredo.frutas.interfaces.ActualizarProductoMVP;
import com.alfredo.frutas.interfaces.AgregarProductoMVP;
import com.alfredo.frutas.model.ActualizarProductoModel;

public class ActualizarProductoPresenter implements ActualizarProductoMVP.Presenter{
    private static final String TAG = "PRESENTER";
    private static ActualizarProductoMVP.Model model;
    public static ActualizarProductoMVP.View view;

    public ActualizarProductoPresenter(ActualizarProductoMVP.View view){
        this.view = view;
    }

    private ActualizarProductoMVP.Model getModel() {
        if (model == null) {
            model = new ActualizarProductoModel(this);
        }
        return model;
    }

    @Override
    public void getContext(Context context) {
        getModel().getContext(context);
    }

    @Override
    public void executeUpdateImage(Integer id, String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas) {
        getModel().doUpdateImage(id, nombre, color, cantidad, imagen, descripcion, beneficios, vitaminas);
    }

    @Override
    public void executeUpdate(Integer id, String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas) {
        getModel().doUpdate(id, nombre, color, cantidad, descripcion, beneficios, vitaminas);
    }

    @Override
    public void onSuccess(String message) {
        ActualizarProductoPresenter.view.onSuccess(message);
    }
}
