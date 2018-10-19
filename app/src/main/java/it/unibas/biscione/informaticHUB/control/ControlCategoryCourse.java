package it.unibas.biscione.informaticHUB.control;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import it.unibas.biscione.informaticHUB.activity.CategoryCourseActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class ControlCategoryCourse {

    private DialogInterface.OnClickListener actionEnroll = new ActionEnroll();
    private DialogInterface.OnClickListener actionCancel = new ActionCancel();
    private final NavigationView.OnNavigationItemSelectedListener menuSwitch = new ControlCategoryCourse.MenuSwitch();

    public DialogInterface.OnClickListener getActionEnroll() {
        return actionEnroll;
    }

    public DialogInterface.OnClickListener getActionCancel() {
        return actionCancel;
    }

    private static final class ActionEnroll implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            CategoryCourseActivity categoryCourseActivity = (CategoryCourseActivity) Application.getInstance().getCurrentActivity();
            AsyncTaskEnroll asyncTaskEnroll = new AsyncTaskEnroll();
            asyncTaskEnroll.execute(categoryCourseActivity.getId());
        }
    }

    private static final class ActionCancel implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }


    public NavigationView.OnNavigationItemSelectedListener getMenuSwitch() {
        return menuSwitch;
    }

    private static final class MenuSwitch implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            CategoryCourseActivity categoryCourseActivity = (CategoryCourseActivity) Application.getInstance().getCurrentActivity();

            switch(item.getItemId())
            {

                case R.id.login_esse3:
                    categoryCourseActivity.showActivityLoginEsse3();
                    categoryCourseActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.my_courses:
                    categoryCourseActivity.showCategoryCourseActivity(9999);
                    categoryCourseActivity.getDrawerLayout().closeDrawers();
                    return  true;
                case R.id.home:
                    categoryCourseActivity.showActivityMain();
                    categoryCourseActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.user_info:
                    categoryCourseActivity.showInfoActivity();
                    categoryCourseActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.telegram:
                    Intent telegram= new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TELEGRAM));
                    Application.getInstance().getCurrentActivity().startActivity(telegram);
                    categoryCourseActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.twitter:
                    Intent twitter = new Intent("android.intent.action.VIEW", Uri.parse(Costants.URI_TWITTER));
                    Application.getInstance().getCurrentActivity().startActivity(twitter);
                    categoryCourseActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.info:
                    categoryCourseActivity.showAppInfoActivity();
                    categoryCourseActivity.getDrawerLayout().closeDrawers();
                    return true;
                case R.id.exam:
                    categoryCourseActivity.showExamActivity();
                    categoryCourseActivity.getDrawerLayout().closeDrawers();
                    return true;
            }
            return false;
        }


    }
}
