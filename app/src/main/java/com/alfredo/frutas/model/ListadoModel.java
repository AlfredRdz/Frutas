package com.alfredo.frutas.model;

import android.content.Context;
import android.util.Log;

import com.alfredo.frutas.Listado;
import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.datamodel.Fruta;
import com.alfredo.frutas.interfaces.ListadoMVP;
import com.alfredo.frutas.interfaces.LoginMVP;

import java.util.List;

public class ListadoModel implements ListadoMVP.Model {
    private static final String TAG = "Model";
    public static ListadoMVP.Presenter presenter;
    private Context context;

    public ListadoModel(ListadoMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void doAdapte() {
        Log.i(TAG, "doLogin: modelo " );
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public List<Fruta> getFrutas() {
        AppDataBase appDataBase = AppDataBase.getInstance(context);

        List<Fruta> lista = appDataBase.frutaDao().getAllFrutas();

        if (lista.size() > 0) {
            ListadoModel.presenter.success(lista);
        }
        return lista;
    }
}
