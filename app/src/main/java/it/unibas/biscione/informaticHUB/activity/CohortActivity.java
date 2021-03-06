package it.unibas.biscione.informaticHUB.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.UserEsse3;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class CohortActivity extends AppCompatActivity {

        private Toolbar toolbar;
        private DrawerLayout drawerLayout;
        private NavigationView navigationView;

        public static final String TAG = CohortActivity.class.getName();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cohort);
            drawerLayout = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.menulaterale);
            configureToolbar();
            setHeader();
            navigationView.setNavigationItemSelectedListener(Application.getInstance().getControlCohort().getMenuSwitch());
        }

    private void setHeader() {
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        UserEsse3 userEsse3 = (UserEsse3) Application.getInstance().getModel().getBean(Costants.USER_ESSE3);
        View headerView = navigationView.getHeaderView(0);
        TextView txtname = headerView.findViewById(R.id.txtName);
        if (userMoodle != null) {
            ((TextView) headerView.findViewById(R.id.txtUserHeader)).setVisibility(0);
            txtname.setText(userMoodle.getSiteInfo().getFullName());
        } else if (userEsse3 != null) {
            ((TextView) headerView.findViewById(R.id.txtUserHeader)).setVisibility(0);
            txtname.setText(userEsse3.getNameString());
        }
    }


    private void configureToolbar() {
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            final ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu); //icona menu
            actionBar.setDisplayHomeAsUpEnabled(true);
            setItemMenu();
        }

    private void setItemMenu() {
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        UserEsse3 userEsse3 = (UserEsse3) Application.getInstance().getModel().getBean(Costants.USER_ESSE3);
        Menu menuNav = navigationView.getMenu();
        if (userEsse3 == null && userMoodle == null){
            MenuItem info = menuNav.findItem(R.id.user_info);
            info.setVisible(false);
        }
        if (userMoodle == null) {
            MenuItem myCourses = menuNav.findItem(R.id.my_courses);
            myCourses.setVisible(false);
            MenuItem moodle = menuNav.findItem(R.id.moodle);
            moodle.setVisible(false);
        } else {
            MenuItem logM = menuNav.findItem(R.id.login_moodle);
            logM.setVisible(false);
        }
        if (userEsse3 == null){
            MenuItem exams = menuNav.findItem(R.id.exam);
            exams.setVisible(false);
        } else {
            MenuItem logEsse3 = menuNav.findItem(R.id.login_esse3);
            logEsse3.setVisible(false);
        }
        MenuItem timetable = menuNav.findItem(R.id.timetable);
        timetable.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId())

        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showTimetableActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), TimetableActivity.class);
        startActivity(i);
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public void showActivityLoginMoodle(){
        Intent intent = new Intent(Application.getInstance().getApplicationContext(), LoginMoodleActivity.class);
        startActivity(intent);
    }

    public void showActivityLoginEsse3(){
        Intent intent = new Intent(Application.getInstance().getApplicationContext(), LoginEsse3Activity.class);
        startActivity(intent);
    }

    public void showCategoryCourseActivity(int param){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), CategoryCourseActivity.class);
        i.putExtra("id", param);
        startActivity(i);
    }

    public void showCategoryCourseActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), CategoryCourseActivity.class);
        startActivity(i);
    }

    public void showInfoActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), UserInfoActivity.class);
        startActivity(i);
    }

    public void showAppInfoActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), AppInfoActivity.class);
        startActivity(i);
    }

    public void showExamActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), ExamActivity.class);
        startActivity(i);
    }

    public void showActivityMain() {
        Intent intent = new Intent(Application.getInstance().getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


}
