package com.alfredo.frutas.presenter;

import android.content.Context;

import com.alfredo.frutas.interfaces.AgregarProductoMVP;
import com.alfredo.frutas.model.AgregarProductoModel;

public class AgregarProductoPresenter implements AgregarProductoMVP.Presenter{
    private static final String TAG = "Presenter";
    private static AgregarProductoMVP.Model model;
    private static AgregarProductoMVP.View view;

    public AgregarProductoPresenter(AgregarProductoMVP.View view){
        this.view = view;
    }

    private AgregarProductoMVP.Model getModel() {
        if (model == null) {
            model = new AgregarProductoModel(this);
        }
        return model;
    }

    @Override
    public void getContext(Context context) {
        getModel().getContext(context);
    }

    @Override
    public void executeRegisterImage(String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas) {
        getModel().doRegisterImage(nombre, color, cantidad, imagen, descripcion, beneficios, vitaminas);
    }

    @Override
    public void executeRegister(String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas) {
        getModel().doRegister(nombre, color, cantidad, descripcion, beneficios, vitaminas);
    }

    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }
}
