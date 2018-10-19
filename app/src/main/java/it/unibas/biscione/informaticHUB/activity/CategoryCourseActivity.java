package it.unibas.biscione.informaticHUB.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Archive;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.UserEsse3;
import it.unibas.biscione.informaticHUB.model.UserMoodle;
import it.unibas.biscione.informaticHUB.view.CategoryCourseView;

public class CategoryCourseActivity extends AppCompatActivity {

    public static final String TAG = CategoryCourseActivity.class.getName();

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ProgressDialog progress;
    private int id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_and_course);
        Bundle param = new Bundle();
        param = getIntent().getExtras();
        if (param != null){
            int id = param.getInt("id");
            CategoryCourseView categoryCourseView = (CategoryCourseView) getSupportFragmentManager().findFragmentById(R.id.cAndCView);
            categoryCourseView.refreshView(id);
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.menulaterale);
        configureToolbar();
        setHeader();
        navigationView.setNavigationItemSelectedListener(Application.getInstance().getControlCategoryCourse().getMenuSwitch());
    }

    private void configureToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setItemMenu();
    }

    public void setItemMenu(){
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        UserEsse3 userEsse3 = (UserEsse3) Application.getInstance().getModel().getBean(Costants.USER_ESSE3);
        Menu menuNav = navigationView.getMenu();
        MenuItem logM = menuNav.findItem(R.id.login_moodle);
        logM.setVisible(false);
        MenuItem myCourses = menuNav.findItem(R.id.my_courses);
        myCourses.setVisible(false);
        if (userEsse3 == null){
            MenuItem exams = menuNav.findItem(R.id.exam);
            exams.setVisible(false);
        } else {
            MenuItem logEsse3 = menuNav.findItem(R.id.login_esse3);
            logEsse3.setVisible(false);
        }
        MenuItem moodle = menuNav.findItem(R.id.moodle);
        moodle.setVisible(false);
        MenuItem timetable = menuNav.findItem(R.id.timetable);
        timetable.setVisible(false);
    }

    private void setHeader(){
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        UserEsse3 userEsse3 = (UserEsse3) Application.getInstance().getModel().getBean(Costants.USER_ESSE3);
        View headerView =  navigationView.getHeaderView(0);
        TextView txtname = headerView.findViewById(R.id.txtName);
        if (userMoodle != null){
            ((TextView) headerView.findViewById(R.id.txtUserHeader)).setVisibility(0);
            txtname.setText(userMoodle.getSiteInfo().getFullName());
        } else if( userEsse3 != null){
            ((TextView) headerView.findViewById(R.id.txtUserHeader)).setVisibility(0);
            txtname.setText(userEsse3.getNameString());
        }
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

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public void showCategoryCourseActivity(int param){
        Intent i = new Intent(this, CategoryCourseActivity.class);
        i.putExtra("id", param);
        startActivity(i);
    }

    public void showSectionActivity(int param){
        Intent i = new Intent(this, SectionActivity.class);
        id = param;
        Archive archive = (Archive) Application.getInstance().getModel().getBean(Costants.ARCHIVE);
        Course c = archive.getCourseById(id);
        Application.getInstance().getModel().putBean(Costants.COURSE, c);
        startActivity(i);
    }

    public void showLoadingDialog(String title, String message){
        progress = ProgressDialog.show(this, title, message, true);
    }

    public void closeLoadingDialog(){
        progress.dismiss();
    }

    public void showErrorDialog(String error){
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    public void showMessageDialog(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void showDialog(String title, String message, int id){
        this.id = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (DialogInterface.OnClickListener) Application.getInstance().getControlCategoryCourse().getActionEnroll());
        builder.setNegativeButton("NO", (DialogInterface.OnClickListener) Application.getInstance().getControlCategoryCourse().getActionCancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void showActivityLoginEsse3(){
        Intent intent = new Intent(Application.getInstance().getApplicationContext(), LoginEsse3Activity.class);
        startActivity(intent);
    }

    public void showActivityMain() {
        Intent intent = new Intent(Application.getInstance().getApplicationContext(), MainActivity.class);
        startActivity(intent);
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
}
