package com.skytalkers.app.moviematcher.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.skytalkers.app.moviematcher.R;

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("**MOVIEMATCHER**", "Pausing the login screen");
    }

    public void onResume() {
        super.onResume();
        Log.d("**MOVIEMATCHER**", "Resuming the login screen");
    }

    public void onLoginButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Login button clicked");
//        Intent intent = new Intent(this, _____________________.class);
//        startActivity(intent);
    }
}
