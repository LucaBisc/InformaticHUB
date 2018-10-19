package it.unibas.biscione.informaticHUB.control;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import it.unibas.biscione.informaticHUB.activity.LoginEsse3Activity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.UserEsse3;
import it.unibas.biscione.informaticHUB.view.ViewLoginEsse3;

public class ControlLoginEsse3 {

    private View.OnClickListener actionLogin = new ActionLogin();
    private View.OnClickListener actionPw = new ActionPw();

    public View.OnClickListener getActionPw() {
        return actionPw;
    }

    public View.OnClickListener getActionLogin() {
        return actionLogin;
    }

    private static final class ActionLogin implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            LoginEsse3Activity loginEsse3Activity = (LoginEsse3Activity) Application.getInstance().getCurrentActivity();
            ViewLoginEsse3 viewLoginEsse3 = loginEsse3Activity.getLoginView();
            String user = viewLoginEsse3.getUsername();
            String pw = viewLoginEsse3.getPw();
            boolean error = validate (user,pw, viewLoginEsse3);
            if (error){
                return;
            }
            UserEsse3 userEsse3 = new UserEsse3();
            userEsse3.setPassword(pw);
            userEsse3.setUsername(user);
            AsyncTaskLoginEsse3 asyncTaskLoginEsse3 = new AsyncTaskLoginEsse3(userEsse3);
            asyncTaskLoginEsse3.execute();
        }

        private boolean validate(String user, String pw, ViewLoginEsse3 viewLoginEsse3) {
            LoginEsse3Activity loginEsse3Activity = (LoginEsse3Activity) Application.getInstance().getCurrentActivity();
            if (user.trim().isEmpty()){
                viewLoginEsse3.showUserError(loginEsse3Activity.getString(R.string.insert_username));
                return true;
            }
            if (pw.trim().isEmpty()){
                viewLoginEsse3.showPWError(loginEsse3Activity.getString(R.string.insert_pw));
                return true;
            }
            return false;
        }
    }

    private static final class ActionPw implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            LoginEsse3Activity loginEsse3Activity = (LoginEsse3Activity) Application.getInstance().getCurrentActivity();
            Intent forgottenPW = new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_REDIRECT_ESSE3));
            loginEsse3Activity.startActivity(forgottenPW);
        }
    }
}
