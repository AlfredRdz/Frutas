package com.alfredo.frutas.model;

import android.content.Context;
import android.util.Log;

import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.datamodel.Fruta;
import com.alfredo.frutas.interfaces.ListadoMVP;
import com.alfredo.frutas.interfaces.LoginMVP;

import java.util.List;

public class ListadoModel {
    private static final String TAG = "Model";
    private static ListadoMVP.Model intance;
    public static ListadoMVP.Presenter presenter;

    public static ListadoMVP.Model getIntance(Context context) {
        if (intance == null) {
            intance = new ListadoMVP.Model(){

                @Override
                public void setPresenter(ListadoMVP.Presenter presenter) {
                    ListadoModel.presenter = presenter;
                }

                @Override
                public void doAdapte() {
                    Log.i(TAG, "doLogin: modelo " );


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
            };
        }
        return intance;
    }
}
