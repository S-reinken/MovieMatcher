package com.skytalkers.app.moviematcher.controllers.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.ToastWrapper;
import com.skytalkers.app.moviematcher.models.UserManager;

public class UserStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String name = getIntent().getStringExtra("name");
        boolean ban = getIntent().getBooleanExtra("ban", false);
        ((TextView) findViewById(R.id.userNameTextView)).setText(name);
        ((TextView) findViewById(R.id.statusTextView)).setText("Status: " + (ban ? "Banned" : "Unlocked"));
        ((Button) findViewById(R.id.statusButton)).setText(ban ? "Unlock" : "Ban");
    }

    public void onStatusButtonClick(View v) {
        UserManager um = new UserManager();
        String name = ((TextView) findViewById(R.id.userNameTextView)).getText().toString();
        um.toggleBan(name);
        boolean ban = um.isBanned(name);
        ((TextView) findViewById(R.id.statusTextView)).setText("Status: " + (ban ? "Banned" : "Unlocked"));
        ((Button) findViewById(R.id.statusButton)).setText(ban ? "Unlock" : "Ban");
        ToastWrapper.show(this, name + " has been " + (ban ? "Banned" : "Unlock"));
    }

}
