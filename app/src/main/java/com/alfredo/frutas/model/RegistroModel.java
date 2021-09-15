package com.alfredo.frutas.model;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.alfredo.frutas.Listado;
import com.alfredo.frutas.Registro;
import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.datamodel.Usuario;
import com.alfredo.frutas.interfaces.RegistroMVP;
import com.alfredo.frutas.presenter.RegistroPresenter;

public class RegistroModel implements RegistroMVP.Model {
    private static final String TAG = "MODEL";
    private static RegistroMVP.Presenter presenter;
    private Context context;

    public RegistroModel(RegistroMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void getUser(String usuario, String contraseña) {
        AppDataBase appDataBase = AppDataBase.getInstance(context);
        Usuario comprobar = appDataBase.usuarioDao().comprobar(usuario);

        if (comprobar == null){
            Log.i(TAG, "No Existe");
            presenter.onSucess("No existe");
            doRegister(usuario, contraseña);
        } else {
            Log.i(TAG, "Ya Existe");
            presenter.onFail("Ya Existe");
        }
    }

    @Override
    public void doRegister(String name, String password) {
        AppDataBase appDataBase = AppDataBase.getInstance(context);

        Usuario usuario = new Usuario();
        usuario.setUsuario(name);
        usuario.setContraseña(password);
        appDataBase.usuarioDao().insertUsuario(usuario);

        presenter.onSucess("Registro exito");
        SharedPreferences preferences = context.getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre" , name);
        editor.putString("contraseña", password);
        editor.commit();
        presenter.onRegister();
    }
}
