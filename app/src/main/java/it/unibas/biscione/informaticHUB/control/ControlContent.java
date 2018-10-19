package it.unibas.biscione.informaticHUB.control;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import it.unibas.biscione.informaticHUB.activity.ContentActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class ControlContent {

    private final NavigationView.OnNavigationItemSelectedListener menuSwitch = new ControlContent.MenuSwitch();

    public NavigationView.OnNavigationItemSelectedListener getMenuSwitch() {
        return menuSwitch;
    }

    private static final class MenuSwitch implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            ContentActivity contentActivity = (ContentActivity) Application.getInstance().getCurrentActivity();

            switch(item.getItemId())
            {
                case R.id.login_esse3:
                    contentActivity.showActivityLoginEsse3();
                    contentActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.my_courses:
                    contentActivity.showCategoryCourseActivity(9999);
                    contentActivity.getDrawerLayout().closeDrawers();
                    return  true;
                case R.id.home:
                    contentActivity.showActivityMain();
                    contentActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.user_info:
                    contentActivity.showInfoActivity();
                    contentActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.telegram:
                    Intent telegram= new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TELEGRAM));
                    Application.getInstance().getCurrentActivity().startActivity(telegram);
                    contentActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.twitter:
                    Intent twitter = new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TWITTER));
                    Application.getInstance().getCurrentActivity().startActivity(twitter);
                    contentActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.info:
                    contentActivity.showAppInfoActivity();
                    contentActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.exam:
                    contentActivity.showExamActivity();
                    contentActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.moodle:
                    contentActivity.showCategoryCourseActivity();
                    contentActivity.getDrawerLayout().closeDrawers();
                    return true;
            }


            return false;
        }


    }
}
