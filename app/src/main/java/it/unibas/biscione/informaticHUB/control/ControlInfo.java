package it.unibas.biscione.informaticHUB.control;

import android.view.View;

import it.unibas.biscione.informaticHUB.activity.UserInfoActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.model.UserEsse3;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class ControlInfo {

    private final View.OnClickListener actionLogout = new ActionLogout();

    public View.OnClickListener getActionLogout() {
        return actionLogout;
    }

    private static final class ActionLogout implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            UserInfoActivity userInfoActivity = (UserInfoActivity) Application.getInstance().getCurrentActivity();
            UserMoodle userMoodle = null;
            UserEsse3 userEsse3 = null;
            Application.getInstance().getModel().putBean(Costants.USER, userMoodle);
            Application.getInstance().getModel().putBean(Costants.USER_ESSE3, userEsse3);
            AsyncTaskTimetable asyncTaskTimetable = new AsyncTaskTimetable();
            asyncTaskTimetable.execute();
            userInfoActivity.showActivityMain();
        }
    }
}
