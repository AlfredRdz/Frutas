package com.alfredo.frutas.interfaces;

import android.content.Context;

public interface AgregarProductoMVP {

    interface View {
        void onSuccess(String message);
    }

    interface Presenter {
        void getContext(Context context);
        void executeRegisterImage(String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas);
        void executeRegister(String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas);
        void onSuccess(String message);
    }

    interface Model {
        void getContext(Context context);
        void doRegisterImage(String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas);
        void doRegister(String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas);
    }
}
