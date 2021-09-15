package com.alfredo.frutas.interfaces;

import android.content.Context;

public interface LoginMVP {

    interface View {
        void showProgressBar(boolean isShowing);
        void onSuccess();
    }

    interface Presenter {
        void getContext(Context context);
        void executeLogin(String name, String password);
        void onResponse(String response);

        void success();
    }

    interface Model {
        void getContext(Context context);
        void doLogin(String name, String password);
    }
}
