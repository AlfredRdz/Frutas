package com.alfredo.frutas.interfaces;

import com.alfredo.frutas.datamodel.Fruta;

import java.util.List;

public interface ListadoMVP {

    interface View {
        void showList(List<Fruta> frutaList);
    }

    interface Presenter {
        void setView(ListadoMVP.View view);
        void executeAdapter();
        void success(List<Fruta> frutaList);
    }

    interface Model {
        void setPresenter(Presenter presenter);
        void doAdapte();
    }
}
