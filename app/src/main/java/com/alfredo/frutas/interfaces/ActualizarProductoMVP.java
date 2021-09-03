package com.alfredo.frutas.interfaces;

public interface ActualizarProductoMVP {
    interface View {
        void onSuccess(String message);
    }

    interface Presenter {
        void setView(ActualizarProductoMVP.View view);
        void executeUpdateImage(Integer id, String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas);
        void executeUpdate(Integer id, String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas);
        void onSuccess(String message);
    }

    interface Model {
        void setPresenter(ActualizarProductoMVP.Presenter presenter);
        void doUpdateImage(Integer id, String nombre, String color, Integer cantidad, String imagen, String descripcion, String beneficios, String vitaminas);
        void doUpdate(Integer id, String nombre, String color, Integer cantidad, String descripcion, String beneficios, String vitaminas);
    }
}
