package edu.galileo.mvp.model;

public interface LoginModel {

    interface OnLoginFinishedListener {

        void onSuccess();

        void onPasswordError();

        void onCancelled();
    }

    void login(String userName, String password, OnLoginFinishedListener listener);

}
