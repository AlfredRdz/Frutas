package com.alfredo.frutas.model;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.alfredo.frutas.Listado;
import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.datamodel.Usuario;
import com.alfredo.frutas.interfaces.LoginMVP;

public class LoginModel {
    private static final String TAG = "Model";
    private static LoginMVP.Model instance;
    public static LoginMVP.Presenter presenter;

    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;

    public static LoginMVP.Model getInstance(Context context) {
        if (instance == null){
            instance = new LoginMVP.Model() {
                @Override
                public void setPresenter(LoginMVP.Presenter presenter) {
                    LoginModel.presenter = presenter;
                }

                @Override
                public void doLogin(String name, String password) {
                    Log.i(TAG, "doLogin: " + name + " " + password);

                    AppDataBase appDataBase = AppDataBase.getInstance(context);

                    Usuario usuario = appDataBase.usuarioDao().login(name, password);

                    if (usuario != null){
                        LoginModel.presenter.success(usuario.getUsuario(), usuario.getContraseña());

                    } else {
                        LoginModel.presenter.onResponse("fallo desde el model" + usuario);
                    }

                }
            };
        }
        return instance;
    }
}
