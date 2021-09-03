package com.alfredo.frutas.presenter;

import android.content.Context;

import com.alfredo.frutas.interfaces.AgregarProductoMVP;
import com.alfredo.frutas.model.AgregarProductoModel;

public class AgregarProductoPresenter {
    private static final String TAG = "Presenter";
    private static AgregarProductoMVP.Presenter instance;
    private static AgregarProductoMVP.View view;

    public static AgregarProductoMVP.Presenter getPresenter(Context context) {
        if (instance == null) {
            instance = new AgregarProductoMVP.Presenter() {
                @Override
                public void setView(AgregarProductoMVP.View view) {
                    AgregarProductoPresenter.view = view;
                }

                @Override
                public void executeRegisterImage(String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas) {
                    AgregarProductoModel.getInstance(context).setPresenter(this);
                    AgregarProductoModel.getInstance(context).doRegisterImage(nombre, color, cantidad, imagen, descripcion, beneficios, vitaminas);
                }

                @Override
                public void executeRegister(String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas) {
                    AgregarProductoModel.getInstance(context).setPresenter(this);
                    AgregarProductoModel.getInstance(context).doRegister(nombre, color, cantidad, descripcion, beneficios, vitaminas);
                }

                @Override
                public void onSuccess(String message) {
                    AgregarProductoPresenter.view.onSuccess(message);
                }

            };
        }
        return instance;
    };
}
