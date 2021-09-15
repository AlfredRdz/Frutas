package com.alfredo.frutas.presenter;

import android.content.Context;

import com.alfredo.frutas.datamodel.Fruta;
import com.alfredo.frutas.interfaces.ListadoMVP;
import com.alfredo.frutas.interfaces.LoginMVP;
import com.alfredo.frutas.model.ListadoModel;
import com.alfredo.frutas.model.LoginModel;

import java.util.List;

public class ListadoPresenter implements ListadoMVP.Presenter{
    private static final String TAG = "Presenter";
    private static ListadoMVP.Model model;
    public static ListadoMVP.View view;

    public ListadoPresenter(ListadoMVP.View view) {
        this.view = view;
    }

    private ListadoMVP.Model getModel() {
        if (model == null) {
            model = new ListadoModel(this);
        }
        return model;
    }

    @Override
    public void executeAdapter(Context context) {
        getModel().getContext(context);
        getModel().getFrutas();
    }

    @Override
    public void success(List<Fruta> frutaList) {
        view.showList(frutaList);
    }
}
