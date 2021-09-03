package com.alfredo.frutas.interfaces;

public interface AgregarProductoMVP {
     interface View {
        void onSuccess(String message);
    }

    interface Presenter {
        void setView(AgregarProductoMVP.View view);
        void executeRegisterImage(String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas);
        void executeRegister(String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas);

        void onSuccess(String message);
    }

    interface Model {
        void setPresenter(AgregarProductoMVP.Presenter presenter);
        void doRegisterImage(String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas);
        void doRegister(String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas);
    }
}
