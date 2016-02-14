package com.skytalkers.app.moviematcher.controllers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.User;
import com.skytalkers.app.moviematcher.models.UserManager;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UserManager um = new UserManager();
        ((TextView) findViewById(R.id.usernameText)).setText(um.getUserName());
        ((TextView) findViewById(R.id.firstText)).setText(um.getUserFirst());
        ((TextView) findViewById(R.id.lastText)).setText(um.getUserLast());
        ((TextView) findViewById(R.id.emailText)).setText(um.getUserEmail());
    }

    public void onBackButtonClick(View v) {
        finish();
    }

}