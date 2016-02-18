package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.UserManager;

public class PrimaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);
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
    }

    public void onLogoutButtonClick(View v) {
        UserManager um = new UserManager();
        um.logout();
        finish();
    }

    public void onBackPressed() {
        UserManager um = new UserManager();
        um.logout();
        super.onBackPressed();
    }

    public void onUsersButtonClick(View v) {
        Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
    }

    public void onUserEditButtonClick(View v) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void onChangePassButtonClick(View v) {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void onViewProfileButtonClick(View v) {
        UserManager um = new UserManager();
        String user = um.getUserName();
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("user_object", user);
        startActivity(intent);
    }

}
