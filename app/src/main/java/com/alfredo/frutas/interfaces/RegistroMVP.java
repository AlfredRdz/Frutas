package com.alfredo.frutas.interfaces;

import android.content.Context;

public interface RegistroMVP {
    interface View {
        void onSuccess(String message);
        void onFail(String message);
        void onRegister();
    }

    interface Presenter {
        void getContext(Context context);
        void existsUser(String usuario, String contraseña);
        void executeRegister(String name, String password);
        void onSucess(String message);
        void onFail(String message);
        void onRegister();
    }

    interface Model {
        void getContext(Context context);
        void getUser(String usuario, String contraseña);
        void doRegister(String name, String password);
    }
}
