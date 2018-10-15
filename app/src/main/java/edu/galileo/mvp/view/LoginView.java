package edu.galileo.mvp.view;

public interface LoginView {

    void showProgressBar(boolean showProgress);

    void setUserNameError(int messageResId);

    void setPasswordError(int messageResId);

}
