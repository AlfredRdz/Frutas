package com.alfredo.frutas.model;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alfredo.frutas.MainActivity;
import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.datamodel.Usuario;
import com.alfredo.frutas.interfaces.LoginMVP;

public class LoginModel implements LoginMVP.Model {
    private static final String TAG = "Model";
    private  LoginMVP.Presenter presenter;
    private Context context;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public LoginModel(LoginMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void doLogin(String name, String password) {
        Log.i(TAG, "doLogin: " + name + " " + password);
        MainActivity activity = new MainActivity();

        AppDataBase appDataBase = AppDataBase.getInstance(context);

        Usuario usuario = appDataBase.usuarioDao().login(name, password);

        if (usuario != null){
            preferences = context.getSharedPreferences("preferences", MODE_PRIVATE);
            editor = preferences.edit();
            editor.putString("nombre" , name);
            editor.putString("contraseña", password);
            editor.commit();
            presenter.success();

        } else {
            presenter.onResponse("fallo desde el model" + usuario);
        }
    }
}
