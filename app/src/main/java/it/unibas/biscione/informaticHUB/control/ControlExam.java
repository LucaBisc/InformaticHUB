package it.unibas.biscione.informaticHUB.control;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import it.unibas.biscione.informaticHUB.activity.ExamActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class ControlExam {
    private final NavigationView.OnNavigationItemSelectedListener menuSwitch = new ControlExam.MenuSwitch();

    public NavigationView.OnNavigationItemSelectedListener getMenuSwitch() {
        return menuSwitch;
    }

    private static final class MenuSwitch implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            ExamActivity examActivity = (ExamActivity) Application.getInstance().getCurrentActivity();

            switch(item.getItemId())
            {
                case R.id.login_moodle:
                    examActivity.showActivityLoginMoodle();
                    examActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.my_courses:
                    examActivity.showCategoryCourseActivity(9999);
                    examActivity.getDrawerLayout().closeDrawers();
                    return  true;
                case R.id.home:
                    examActivity.showActivityMain();
                    examActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.user_info:
                    examActivity.showInfoActivity();
                    examActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.telegram:
                    Intent telegram= new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TELEGRAM));
                    Application.getInstance().getCurrentActivity().startActivity(telegram);
                    examActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.twitter:
                    Intent twitter = new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TWITTER));
                    Application.getInstance().getCurrentActivity().startActivity(twitter);
                    examActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.info:
                    examActivity.showAppInfoActivity();
                    examActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.moodle:
                    examActivity.showCategoryCourseActivity();
                    examActivity.getDrawerLayout().closeDrawers();
                    return true;
            }


            return false;
        }


    }
}

