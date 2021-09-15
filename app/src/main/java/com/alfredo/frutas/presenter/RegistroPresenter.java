package com.alfredo.frutas.presenter;

import android.content.Context;

import com.alfredo.frutas.interfaces.RegistroMVP;
import com.alfredo.frutas.model.RegistroModel;

public class RegistroPresenter implements RegistroMVP.Presenter {
    private static final String TAG = "PRESENTER";
    private static RegistroMVP.Model model;
    public static RegistroMVP.View view;

    public RegistroPresenter(RegistroMVP.View view){
        this.view = view;
    }

    private RegistroMVP.Model getModel(){
        if (model == null) {
            model = new RegistroModel(this);
        }
        return model;
    }

    @Override
    public void getContext(Context context) {
        getModel().getContext(context);
    }

    @Override
    public void existsUser(String usuario, String contraseña) {
        getModel().getUser(usuario, contraseña);
    }

    @Override
    public void executeRegister(String name, String password) {
        getModel().doRegister(name, password);
    }

    @Override
    public void onSucess(String message) {
        view.onSuccess(message);
    }

    @Override
    public void onFail(String message) {
        view.onFail(message);
    }

    @Override
    public void onRegister() {
        view.onRegister();
    }
}
