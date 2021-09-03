package com.alfredo.frutas.model;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.alfredo.frutas.Listado;
import com.alfredo.frutas.Registro;
import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.datamodel.Usuario;
import com.alfredo.frutas.interfaces.RegistroMVP;
import com.alfredo.frutas.presenter.RegistroPresenter;

public class RegistroModel {
    private static final String TAG = "MODEL";
    private static RegistroMVP.Model instance;
    private static RegistroMVP.Presenter presenter;

    public static RegistroMVP.Model getInstance(Context context) {
        if (instance == null) {
            instance = new RegistroMVP.Model() {
                @Override
                public void setPresenter(RegistroMVP.Presenter presenter) {
                    RegistroModel.presenter = presenter;
                }

                @Override
                public void doRegister(String name, String password) {
                    AppDataBase appDataBase = AppDataBase.getInstance(context);

                    Usuario usuario = new Usuario();
                    usuario.setUsuario(name);
                    usuario.setContraseña(password);
                    appDataBase.usuarioDao().insertUsuario(usuario);

                    RegistroModel.presenter.onSucess("Registro exito");
                    RegistroModel.presenter.onRegister(name, password);
                }
            };
        }
        return instance;
    }
}
