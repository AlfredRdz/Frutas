package com.alfredo.frutas.presenter;

import android.content.Context;
import android.util.Log;

import com.alfredo.frutas.interfaces.LoginMVP;
import com.alfredo.frutas.model.LoginModel;

public class LoginPresenter {

    private static final String  TAG = "Presenter";
    private static LoginMVP.Presenter instance;
    private static LoginMVP.View view;

    public static LoginMVP.Presenter getPresenter(Context context) {
        if (instance == null) {

            instance = new LoginMVP.Presenter() {

                @Override
                public void setView(LoginMVP.View view) {
                    LoginPresenter.view = view;
                }

                @Override
                public void executeLogin(String name, String password) {
                    LoginPresenter.view.showProgressBar(true);
                    LoginModel.getInstance(context).setPresenter(this);
                    LoginModel.getInstance(context).doLogin(name, password);
                }

                @Override
                public void onResponse(String response) {
                    LoginPresenter.view.showProgressBar(true);
                    Log.i(TAG, "onResponse: " + response);
                }

                @Override
                public void success(String name, String password) {
                    LoginPresenter.view.onSuccess(name, password);
                }
            };
        }
        return instance;
    }
}
