package it.unibas.biscione.informaticHUB.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.R;

public class UserInfoActivity extends AppCompatActivity{

    private static final String TAG = PageActivity.class.getName();

    private Toolbar toolbar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
    }

    public void showActivityMain() {
        Intent intent = new Intent(Application.getInstance().getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
