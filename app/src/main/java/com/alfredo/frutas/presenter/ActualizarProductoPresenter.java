package com.alfredo.frutas.presenter;

import android.content.Context;

import com.alfredo.frutas.interfaces.ActualizarProductoMVP;
import com.alfredo.frutas.interfaces.AgregarProductoMVP;
import com.alfredo.frutas.model.ActualizarProductoModel;

public class ActualizarProductoPresenter {
    private static final String TAG = "PRESENTER";
    private static ActualizarProductoMVP.Presenter instance;
    public static ActualizarProductoMVP.View view;

    public static ActualizarProductoMVP.Presenter getPresenter(Context context) {
        if (instance == null) {
            instance = new ActualizarProductoMVP.Presenter() {
                @Override
                public void setView(ActualizarProductoMVP.View view) {
                    ActualizarProductoPresenter.view = view;
                }

                @Override
                public void executeUpdateImage(Integer id, String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas) {
                    ActualizarProductoModel.getInstance(context).setPresenter(this);
                    ActualizarProductoModel.getInstance(context).doUpdateImage(id, nombre, color, cantidad, imagen, descripcion, beneficios, vitaminas);
                }

                @Override
                public void executeUpdate(Integer id, String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas) {
                    ActualizarProductoModel.getInstance(context).setPresenter(this);
                    ActualizarProductoModel.getInstance(context).doUpdate(id, nombre, color, cantidad, descripcion, beneficios, vitaminas);
                }

                @Override
                public void onSuccess(String message) {
                    ActualizarProductoPresenter.view.onSuccess(message);
                }
            };
        }
        return instance;
    }
}
