package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
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
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Logout User
     * @param v Current View
     */
    public void onLogoutButtonClick(View v) {
        final UserManager um = new UserManager();
        um.logout();
        finish();
    }

    /**
     * Logout User
     */
    public void onBackPressed() {
        final UserManager um = new UserManager();
        um.logout();
        super.onBackPressed();
    }

    /**
     * Bring up user list
     * @param v Current View
     */
    public void onUsersButtonClick(View v) {
        final Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
    }

    /**
     * Edit User profile
     * @param v Current View
     */
    public void onUserEditButtonClick(View v) {
        final Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Start ChangePassword Activity
     * @param v Current View
     */
    public void onChangePassButtonClick(View v) {
        final Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }
}
