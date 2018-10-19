package it.unibas.biscione.informaticHUB.control;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.activity.CohortActivity;

public class ControlCohort {

    private final NavigationView.OnNavigationItemSelectedListener menuSwitch = new ControlCohort.MenuSwitch();

    public NavigationView.OnNavigationItemSelectedListener getMenuSwitch() {
        return menuSwitch;
    }

    private static final class MenuSwitch implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            CohortActivity cohortActivity = (CohortActivity) Application.getInstance().getCurrentActivity();

            switch(item.getItemId())
            {
                case R.id.login_moodle:
                    cohortActivity.showActivityLoginMoodle();
                    cohortActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.login_esse3:
                    cohortActivity.showActivityLoginEsse3();
                    cohortActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.my_courses:
                    cohortActivity.showCategoryCourseActivity(9999);
                    cohortActivity.getDrawerLayout().closeDrawers();
                    return  true;
                case R.id.home:
                    cohortActivity.showActivityMain();
                    cohortActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.user_info:
                    cohortActivity.showInfoActivity();
                    cohortActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.telegram:
                    Intent telegram= new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TELEGRAM));
                    Application.getInstance().getCurrentActivity().startActivity(telegram);
                    cohortActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.twitter:
                    Intent twitter = new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TWITTER));
                    Application.getInstance().getCurrentActivity().startActivity(twitter);
                    cohortActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.info:
                    cohortActivity.showAppInfoActivity();
                    cohortActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.exam:
                    cohortActivity.showExamActivity();
                    cohortActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.moodle:
                    cohortActivity.showCategoryCourseActivity();
                    cohortActivity.getDrawerLayout().closeDrawers();
                    return true;
            }
            return false;
        }


    }
}
