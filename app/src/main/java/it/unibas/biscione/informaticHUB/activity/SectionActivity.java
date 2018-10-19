package it.unibas.biscione.informaticHUB.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import it.unibas.biscione.informaticHUB.model.UserEsse3;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class SectionActivity extends AppCompatActivity {

    private static final String TAG = SectionActivity.class.getName();

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ProgressDialog progress;

    private int id;
    private String htmlPage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.menulaterale);
        configureToolbar();
        setHeader();
        navigationView.setNavigationItemSelectedListener(Application.getInstance().getControlSection().getMenuSwitch());
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
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setItemMenu();
    }

    private void setItemMenu() {
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        UserEsse3 userEsse3 = (UserEsse3) Application.getInstance().getModel().getBean(Costants.USER_ESSE3);
        Menu menuNav = navigationView.getMenu();
        MenuItem logM = menuNav.findItem(R.id.login_moodle);
        logM.setVisible(false);
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

    public void showErrorDialog(String error){
        Toast.makeText(Application.getInstance(), error, Toast.LENGTH_LONG).show();
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

    public int getId() {
        return id;
    }

    public void showCategoryCourseActivity(int param){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), CategoryCourseActivity.class);
        i.putExtra("id", param);
        startActivity(i);
    }

    public void showActivityLoginEsse3(){
        Intent intent = new Intent(Application.getInstance().getApplicationContext(), LoginEsse3Activity.class);
        startActivity(intent);
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public void showActivityMain() {
        Intent intent = new Intent(Application.getInstance().getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void showInfoActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), UserInfoActivity.class);
        startActivity(i);
    }

    public void showContentActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), ContentActivity.class);
        startActivity(i);
    }

    public void showForumActivity(int param){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), ForumActivity.class);
        id = param;
        i.putExtra("id", param);
        startActivity(i);
    }

    public void showPageActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), PageActivity.class);
        startActivity(i);
    }

    public void showMessageDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void showLoadingDialog(String title, String message) {
        progress = ProgressDialog.show(this, title, message, true);
    }

    public void closeLoadingDialog(){
        progress.dismiss();
    }

    public String getHtmlPage() {
        return htmlPage;
    }

    public void setHtmlPage(String htmlPage) {
        this.htmlPage = htmlPage;
    }

    public void showAppInfoActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), AppInfoActivity.class);
        startActivity(i);
    }

    public void showExamActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), ExamActivity.class);
        startActivity(i);
    }

    public void showCategoryCourseActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), CategoryCourseActivity.class);
        startActivity(i);
    }


}
