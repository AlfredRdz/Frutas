package com.alfredo.frutas.presenter;

import android.content.Context;

import com.alfredo.frutas.datamodel.Fruta;
import com.alfredo.frutas.interfaces.ListadoMVP;
import com.alfredo.frutas.interfaces.LoginMVP;
import com.alfredo.frutas.model.ListadoModel;

import java.util.List;

public class ListadoPresenter {
    private static final String TAG = "Presenter";
    private static ListadoMVP.Presenter instance;
    public static ListadoMVP.View view;

    public static ListadoMVP.Presenter getPresenter(Context context){
        if (instance == null){
            instance = new ListadoMVP.Presenter() {
                @Override
                public void setView(ListadoMVP.View view) {
                    ListadoPresenter.view = view;
                }

                @Override
                public void executeAdapter() {
                    ListadoModel.getIntance(context).setPresenter(this);
                    ListadoModel.getIntance(context).doAdapte();
                }

                @Override
                public void success(List<Fruta> frutaList) {
                    ListadoPresenter.view.showList(frutaList);
                }
            };
        }

        return instance;
    };
}
