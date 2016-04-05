package com.skytalkers.app.moviematcher.controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.ToastWrapper;
import com.skytalkers.app.moviematcher.models.UserManager;

public class UserStatusActivity extends AppCompatActivity {

    /**
     * Occurs on creation of this activity
     * @param savedInstanceState Android instance data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_status);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final String name = getIntent().getStringExtra("name");
        final boolean ban = getIntent().getBooleanExtra("ban", false);
        ((TextView) findViewById(R.id.userNameTextView)).setText(name);
        ((TextView) findViewById(R.id.statusTextView)).setText("Status: " + (ban ? "Banned" : "Unlocked"));
        ((Button) findViewById(R.id.statusButton)).setText(ban ? "Unlock" : "Ban");
    }


    /**
     * Displays banned status of user
     * @param v Button clicked
     */
    public void onStatusButtonClick(View v) {
        final UserManager um = new UserManager();
        final String name = ((TextView) findViewById(R.id.userNameTextView)).getText().toString();
        um.toggleBan(name);
        final boolean ban = um.isBanned(name);
        ((TextView) findViewById(R.id.statusTextView)).setText("Status: " + (ban ? "Banned" : "Unlocked"));
        ((Button) findViewById(R.id.statusButton)).setText(ban ? "Unlock" : "Ban");
        ToastWrapper.show(this, name + " has been " + (ban ? "Banned" : "Unlock"));
    }

}
