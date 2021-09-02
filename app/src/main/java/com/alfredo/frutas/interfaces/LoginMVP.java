package com.alfredo.frutas.interfaces;

public interface LoginMVP {

    interface View {
        void showProgressBar(boolean isShowing);
        void onSuccess(String name, String password);
    }

    interface Presenter {
        void setView(LoginMVP.View view);
        void executeLogin(String name, String password);
        void onResponse(String response);

        void success(String name, String password);
    }

    interface Model {
        void setPresenter(LoginMVP.Presenter presenter);
        void doLogin(String name, String password);
    }
}
