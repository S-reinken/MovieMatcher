package com.skytalkers.app.moviematcher.controllers.Activities;

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
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String name = getIntent().getStringExtra("user");
        UserManager um = new UserManager();
        ((TextView) findViewById(R.id.usernameText)).setText(name);
        ((TextView) findViewById(R.id.firstText)).setText(um.findUserFirst(name));
        ((TextView) findViewById(R.id.lastText)).setText(um.findUserLast(name));
        ((TextView) findViewById(R.id.emailText)).setText(um.findUserEmail(name));
        ((TextView) findViewById(R.id.majorText)).setText(um.findUserMajor(name));
    }

    public void onBackButtonClick(View v) {
        finish();
    }

}
