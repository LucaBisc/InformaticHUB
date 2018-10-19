package it.unibas.biscione.informaticHUB.control;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import it.unibas.biscione.informaticHUB.activity.PageActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class ControlPage {

    private final NavigationView.OnNavigationItemSelectedListener menuSwitch = new ControlPage.MenuSwitch();

    public NavigationView.OnNavigationItemSelectedListener getMenuSwitch() {
        return menuSwitch;
    }

    private static final class MenuSwitch implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            PageActivity pageActivity = (PageActivity) Application.getInstance().getCurrentActivity();

            switch(item.getItemId())
            {

                case R.id.login_esse3:
                    pageActivity.showActivityLoginEsse3();
                    pageActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.my_courses:
                    pageActivity.showCategoryCourseActivity(9999);
                    pageActivity.getDrawerLayout().closeDrawers();
                    return  true;
                case R.id.home:
                    pageActivity.showActivityMain();
                    pageActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.user_info:
                    pageActivity.showInfoActivity();
                    pageActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.telegram:
                    Intent telegram= new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TELEGRAM));
                    Application.getInstance().getCurrentActivity().startActivity(telegram);
                    pageActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.twitter:
                    Intent twitter = new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TWITTER));
                    Application.getInstance().getCurrentActivity().startActivity(twitter);
                    pageActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.info:
                    pageActivity.showAppInfoActivity();
                    pageActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.exam:
                    pageActivity.showExamActivity();
                    pageActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.moodle:
                    pageActivity.showCategoryCourseActivity();
                    pageActivity.getDrawerLayout().closeDrawers();
                    return true;
            }

            return false;
        }


    }
}
