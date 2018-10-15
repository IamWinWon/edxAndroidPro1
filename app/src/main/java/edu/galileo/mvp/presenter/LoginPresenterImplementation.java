package edu.galileo.mvp.presenter;

import android.text.TextUtils;

import edu.galileo.mvp.R;
import edu.galileo.mvp.model.LoginModel;
import edu.galileo.mvp.model.LoginModelImplementation;
import edu.galileo.mvp.view.LoginView;

public class LoginPresenterImplementation implements LoginPresenter {

    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImplementation(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImplementation();
    }

    @Override
    public void validateCredentials(String userName, String password) {
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            loginView.setPasswordError(R.string.error_invalid_password);
            return;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(userName)) {
            loginView.setUserNameError(R.string.error_field_required);
            return;
        } else if (!isEmailValid(userName)) {
            loginView.setUserNameError(R.string.error_invalid_email);
            return;
        }

        loginView.showProgressBar(true);
        loginModel.login(userName, password);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
