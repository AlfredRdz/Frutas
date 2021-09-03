package com.alfredo.frutas.interfaces;

public interface RegistroMVP {
    interface View {
        void onSuccess(String message);
        void onRegister(String name, String password);
    }

    interface Presenter {
        void setView(RegistroMVP.View view);
        void executeRegister(String name, String password);
        void onSucess(String message);
        void onRegister(String name, String password);
    }

    interface Model {
        void setPresenter(RegistroMVP.Presenter presenter);
        void doRegister(String name, String password);
    }
}
