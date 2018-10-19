package it.unibas.biscione.informaticHUB.control;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;

import it.unibas.biscione.informaticHUB.activity.ForumActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class ControlForum {

    private final NavigationView.OnNavigationItemSelectedListener menuSwitch = new ControlForum.MenuSwitch();

    private final View.OnClickListener addNews = new ControlForum.ActionAdd();

    public View.OnClickListener getAddNews() {
        return addNews;
    }

    public NavigationView.OnNavigationItemSelectedListener getMenuSwitch() {
        return menuSwitch;
    }

    private static final class MenuSwitch implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            ForumActivity forumActivity = (ForumActivity) Application.getInstance().getCurrentActivity();

            switch(item.getItemId())
            {

                case R.id.login_esse3:
                    forumActivity.showActivityLoginEsse3();
                    forumActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.my_courses:
                    forumActivity.showCategoryCourseActivity(9999);
                    forumActivity.getDrawerLayout().closeDrawers();
                    return  true;
                case R.id.home:
                    forumActivity.showActivityMain();
                    forumActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.user_info:
                    forumActivity.showInfoActivity();
                    forumActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.telegram:
                    Intent telegram= new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TELEGRAM));
                    Application.getInstance().getCurrentActivity().startActivity(telegram);
                    forumActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.twitter:
                    Intent twitter = new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TWITTER));
                    Application.getInstance().getCurrentActivity().startActivity(twitter);
                    forumActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.info:
                    forumActivity.showAppInfoActivity();
                    forumActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.moodle:
                    forumActivity.showCategoryCourseActivity();
                    forumActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.exam:
                    forumActivity.showExamActivity();
                    forumActivity.getDrawerLayout().closeDrawers();
                    return true;
            }

            return false;
        }


    }

    private final class ActionAdd implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            ForumActivity forumActivity = (ForumActivity) Application.getInstance().getCurrentActivity();
            forumActivity.showAddNewsActivity();
        }
    }
}
