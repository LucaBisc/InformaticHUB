package it.unibas.biscione.informaticHUB.control;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import it.unibas.biscione.informaticHUB.activity.LoginMoodleActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.UserMoodle;
import it.unibas.biscione.informaticHUB.view.ViewLoginMoodle;

public class ControlLoginMoodle {

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
            LoginMoodleActivity loginMoodleActivity = (LoginMoodleActivity) Application.getInstance().getCurrentActivity();
            ViewLoginMoodle viewLoginMoodle = loginMoodleActivity.getVistaLogin();
            String user = viewLoginMoodle.getUsername();
            String pw = viewLoginMoodle.getPw();
            boolean error = validate (user,pw, viewLoginMoodle);
            if (error){
                return;
            }
            UserMoodle userMoodle = new UserMoodle();
            userMoodle.setPw(pw);
            userMoodle.setUsername(user);
            AsyncTaskLoginMoodle asyncTaskLoginMoodle = new AsyncTaskLoginMoodle(userMoodle);
            asyncTaskLoginMoodle.execute();
        }

        private boolean validate(String user, String pw, ViewLoginMoodle viewLoginMoodle) {
            LoginMoodleActivity loginMoodleActivity = (LoginMoodleActivity) Application.getInstance().getCurrentActivity();
            if (user.trim().isEmpty()){
                viewLoginMoodle.showUserError(loginMoodleActivity.getString(R.string.insert_username));
                return true;
            }
            if (pw.trim().isEmpty()){
                viewLoginMoodle.showPWError(loginMoodleActivity.getString(R.string.insert_pw));
                return true;
            }
            return false;
        }
    }

    private static final class ActionPw implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            LoginMoodleActivity loginMoodleActivity = (LoginMoodleActivity) Application.getInstance().getCurrentActivity();
            Intent forgottenPW = new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_REDIRECT_MOODLE));
            loginMoodleActivity.startActivity(forgottenPW);
        }
    }
}
