package it.unibas.biscione.informaticHUB.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.view.ViewLoginMoodle;

public class LoginMoodleActivity extends AppCompatActivity {

    public static final String TAG = LoginMoodleActivity.class.getSimpleName();
    private ProgressDialog progress;

    private final String defaultUnameMoodleValue = "";
    private String unameMoodleValue;

    private final String defaultPasswordMoodleValue = "";
    private String passwordMoodleValue;

    public ViewLoginMoodle getVistaLogin(){
        return (ViewLoginMoodle) getSupportFragmentManager().findFragmentById(R.id.viewLoginMoodle);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_moodle);
    }

    @Override
    public void onPause() {
        super.onPause();
        savePreferences();
    }


    public void showLoadingDialog(String title, String message){
        progress = ProgressDialog.show(this, title, message, true);
    }

    public void closeLoadingDialog(){
        progress.dismiss();
    }

    public void showErrorDialog(String messaggio) {
        Toast.makeText(Application.getInstance(), messaggio, Toast.LENGTH_LONG).show();
    }

    public void showCategoryCourseActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), CategoryCourseActivity.class);
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }

    public void savePreferences() {
        SharedPreferences settings = getSharedPreferences(Costants.PREFS_NAME_MOODLE , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        unameMoodleValue = getVistaLogin().getUsername();
        passwordMoodleValue = getVistaLogin().getPw();
        System.out.println("onPause save name: " + unameMoodleValue);
        System.out.println("onPause save password: " + passwordMoodleValue);
        editor.putString(Costants.PREF_UNAME_MOODLE, unameMoodleValue);
        editor.putString(Costants.PREF_PASSWORD_MOODLE, passwordMoodleValue);
        editor.commit();
    }

    public void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(Costants.PREFS_NAME_MOODLE,
                Context.MODE_PRIVATE);
        unameMoodleValue = settings.getString(Costants.PREF_UNAME_MOODLE, defaultUnameMoodleValue);
        passwordMoodleValue = settings.getString(Costants.PREF_PASSWORD_MOODLE, defaultPasswordMoodleValue);
        getVistaLogin().setTextPW(passwordMoodleValue);
        getVistaLogin().setTextUser(unameMoodleValue);
        System.out.println("onResume load name: " + unameMoodleValue);
        System.out.println("onResume load password: " + passwordMoodleValue);
    }
}
