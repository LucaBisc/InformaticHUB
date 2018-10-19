package it.unibas.biscione.informaticHUB.control;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.view.MenuItem;

import it.unibas.biscione.informaticHUB.activity.MainActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class ControlMain {

    private final OnNavigationItemSelectedListener menuSwitch = new MenuSwitch();


    public OnNavigationItemSelectedListener getMenuSwitch() {
        return menuSwitch;
    }

    private static final class MenuSwitch implements OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            MainActivity mainActivity = (MainActivity) Application.getInstance().getCurrentActivity();

            switch(item.getItemId())
            {
                case R.id.login_moodle:
                    mainActivity.showActivityLoginMoodle();
                    mainActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.login_esse3:
                    mainActivity.showActivityLoginEsse3();
                    mainActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.my_courses:
                    mainActivity.showCategoryCourseActivity(9999);
                    mainActivity.getDrawerLayout().closeDrawers();
                    return  true;
                case R.id.moodle:
                    mainActivity.showCategoryCourseActivity();
                    mainActivity.getDrawerLayout().closeDrawers();
                    return  true;
                case R.id.user_info:
                    mainActivity.showInfoActivity();
                    mainActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.timetable:
                    mainActivity.showTimetableActivity();
                    mainActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.telegram:
                    Intent telegram= new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TELEGRAM));
                    Application.getInstance().getCurrentActivity().startActivity(telegram);
                    mainActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.twitter:
                    Intent twitter = new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TWITTER));
                    Application.getInstance().getCurrentActivity().startActivity(twitter);
                    mainActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.info:
                    mainActivity.showAppInfoActivity();
                    mainActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.exam:
                    mainActivity.showExamActivity();
                    mainActivity.getDrawerLayout().closeDrawers();
                    return true;
            }

            return false;
        }


    }
}
