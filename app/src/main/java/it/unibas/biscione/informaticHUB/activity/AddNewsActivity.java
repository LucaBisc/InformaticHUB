package it.unibas.biscione.informaticHUB.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import it.unibas.biscione.informaticHUB.view.ViewAddNews;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.R;

public class AddNewsActivity extends AppCompatActivity {

    public static final String TAG = AddNewsActivity.class.getSimpleName();
    private ProgressDialog progress;
    private Toolbar toolbar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
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

    public ViewAddNews getAddNewsView() {
        return (ViewAddNews) getSupportFragmentManager().findFragmentById(R.id.viewAddNews);
    }

    public void showForumActivity(int param){
        Intent i = new Intent(Application.getInstance().getApplicationContext(), ForumActivity.class);
        i.putExtra("id", param);
        startActivity(i);
    }
}
