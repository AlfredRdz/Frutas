package com.alfredo.frutas.presenter;

import android.content.Context;

import com.alfredo.frutas.interfaces.RegistroMVP;
import com.alfredo.frutas.model.RegistroModel;

public class RegistroPresenter {
    private static final String TAG = "PRESENTER";
    private static RegistroMVP.Presenter instance;
    public static RegistroMVP.View view;

    public static RegistroMVP.Presenter getPresenter(Context context) {
        if (instance == null) {
            instance = new RegistroMVP.Presenter() {
                @Override
                public void setView(RegistroMVP.View view) {
                    RegistroPresenter.view = view;
                }

                @Override
                public void executeRegister(String name, String password) {
                    RegistroModel.getInstance(context).setPresenter(this);
                    RegistroModel.getInstance(context).doRegister(name, password);
                }

                @Override
                public void onSucess(String message) {
                    RegistroPresenter.view.onSuccess(message);
                }

                @Override
                public void onRegister(String name, String password) {
                    RegistroPresenter.view.onRegister(name, password);
                }
            };
        }
        return instance;
    }
}
