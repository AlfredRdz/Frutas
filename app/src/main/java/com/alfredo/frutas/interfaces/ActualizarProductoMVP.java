package com.alfredo.frutas.interfaces;

import android.content.Context;

public interface ActualizarProductoMVP {
    interface View {
        void onSuccess(String message);
    }

    interface Presenter {
        void getContext(Context context);
        void executeUpdateImage(Integer id, String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas);
        void executeUpdate(Integer id, String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas);
        void onSuccess(String message);
    }

    interface Model {
        void getContext(Context context);
        void doUpdateImage(Integer id, String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas);
        void doUpdate(Integer id, String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas);
    }
}
