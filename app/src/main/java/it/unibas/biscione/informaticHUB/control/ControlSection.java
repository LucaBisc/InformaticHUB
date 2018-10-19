package it.unibas.biscione.informaticHUB.control;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import it.unibas.biscione.informaticHUB.activity.SectionActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class ControlSection {

    private final NavigationView.OnNavigationItemSelectedListener menuSwitch = new ControlSection.MenuSwitch();

    public NavigationView.OnNavigationItemSelectedListener getMenuSwitch() {
        return menuSwitch;
    }

    private static final class MenuSwitch implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();

            switch(item.getItemId())
            {

                case R.id.login_esse3:
                    sectionActivity.showActivityLoginEsse3();
                    sectionActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.my_courses:
                    sectionActivity.showCategoryCourseActivity(9999);
                    sectionActivity.getDrawerLayout().closeDrawers();
                    return  true;
                case R.id.home:
                    sectionActivity.showActivityMain();
                    sectionActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.user_info:
                    sectionActivity.showInfoActivity();
                    sectionActivity.getDrawerLayout().closeDrawers();
                return true;
                case R.id.telegram:
                    Intent telegram= new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TELEGRAM));
                    Application.getInstance().getCurrentActivity().startActivity(telegram);
                    sectionActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.twitter:
                    Intent twitter = new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TWITTER));
                    Application.getInstance().getCurrentActivity().startActivity(twitter);
                    sectionActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.info:
                    sectionActivity.showAppInfoActivity();
                    sectionActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.exam:
                    sectionActivity.showExamActivity();
                    sectionActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.moodle:
                    sectionActivity.showCategoryCourseActivity();
                    sectionActivity.getDrawerLayout().closeDrawers();
                    return true;
            }

            return false;
        }


    }
}
