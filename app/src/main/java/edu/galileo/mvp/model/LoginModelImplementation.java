package edu.galileo.mvp.model;

import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;

import edu.galileo.mvp.event.CancelledEvent;
import edu.galileo.mvp.event.PasswordErrorEvent;
import edu.galileo.mvp.event.SuccessEvent;

public class LoginModelImplementation implements LoginModel {

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "t@galileo.du:qwerty", "test2@galileo.edu:testtest"
    };

    @Override
    public void login(String userName, String password) {
        new UserLoginTask(userName, password).execute();
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                EventBus.getDefault().post(new SuccessEvent());
            } else {
                EventBus.getDefault().post(new PasswordErrorEvent());
            }
        }

        @Override
        protected void onCancelled() {
            EventBus.getDefault().post(new CancelledEvent());
        }
    }
}
