package com.alfredo.frutas.interfaces;

import android.content.Context;

import com.alfredo.frutas.datamodel.Fruta;

import java.util.List;

public interface ListadoMVP {

    interface View {
        void showList(List<Fruta> frutaList);
    }

    interface Presenter {
        void executeAdapter(Context context);
        void success(List<Fruta> frutaList);
    }

    interface Model {
        void doAdapte();
        void getContext(Context context);
        List<Fruta> getFrutas();
    }
}
