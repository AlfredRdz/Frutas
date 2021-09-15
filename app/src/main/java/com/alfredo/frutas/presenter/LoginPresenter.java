package com.alfredo.frutas.presenter;

import android.content.Context;
import android.util.Log;

import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.interfaces.LoginMVP;
import com.alfredo.frutas.model.LoginModel;

public class LoginPresenter implements LoginMVP.Presenter {

    private static final String  TAG = "Presenter";
    private static LoginMVP.Model model;
    private static LoginMVP.View view;

    public LoginPresenter(LoginMVP.View view){
        this.view = view;
    }

    private LoginMVP.Model getModel() {
        if (model == null) {
            model = new LoginModel(this);
        }
        return model;
    }

    @Override
    public void getContext(Context context) {
        getModel().getContext(context);
    }

    @Override
    public void executeLogin(String name, String password) {
        view.showProgressBar(true);
        getModel().doLogin(name, password);
    }

    @Override
    public void onResponse(String response) {
        view.showProgressBar(true);
        Log.i(TAG, "onResponse: " + response);
    }

    @Override
    public void success() {
        view.onSuccess();
    }
}
