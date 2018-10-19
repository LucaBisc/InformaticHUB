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
import it.unibas.biscione.informaticHUB.view.ViewLoginEsse3;

public class LoginEsse3Activity extends AppCompatActivity {

    public static final String TAG = LoginEsse3Activity.class.getSimpleName();
    private ProgressDialog progress;

    private final String defaultUnameEsse3Value = "";
    private String unameEsse3Value;

    private final String defaultPasswordEsse3Value = "";
    private String passwordEsse3Value;

    public ViewLoginEsse3 getLoginView(){
        return (ViewLoginEsse3) getSupportFragmentManager().findFragmentById(R.id.viewLoginEsse3);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_esse3);
    }

    @Override
    public void onPause() {
        super.onPause();
        savePreferences();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }


    public void showLoadingDialog(String title, String message){
        progress = ProgressDialog.show(this, title, message, true);
    }

    public void closeLoadingDialog(){
        progress.dismiss();
    }

    public void showErrorDialog(String message) {
        Toast.makeText(Application.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    public void savePreferences() {
        SharedPreferences settings = getSharedPreferences(Costants.PREFS_NAME_ESSE3 , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        unameEsse3Value = getLoginView().getUsername();
        passwordEsse3Value = getLoginView().getPw();
        System.out.println("onPause save name: " + unameEsse3Value);
        System.out.println("onPause save password: " + passwordEsse3Value);
        editor.putString(Costants.PREF_UNAME_ESSE3, unameEsse3Value);
        editor.putString(Costants.PREF_PASSWORD_ESSE3, passwordEsse3Value);
        editor.commit();
    }

    public void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(Costants.PREFS_NAME_ESSE3,
                Context.MODE_PRIVATE);
        unameEsse3Value = settings.getString(Costants.PREF_UNAME_ESSE3, defaultUnameEsse3Value);
        passwordEsse3Value = settings.getString(Costants.PREF_PASSWORD_ESSE3, defaultPasswordEsse3Value);
        getLoginView().setTextPW(passwordEsse3Value);
        getLoginView().setTextUser(unameEsse3Value);
        System.out.println("onResume load name: " + unameEsse3Value);
        System.out.println("onResume load password: " + passwordEsse3Value);
    }

    public void showExamActivity(){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), ExamActivity.class);
        startActivity(i);
    }

}
