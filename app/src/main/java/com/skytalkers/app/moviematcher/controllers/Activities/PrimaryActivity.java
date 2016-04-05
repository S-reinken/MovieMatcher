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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Log user out
     * @param v Button clicked
     */
    public void onLogoutButtonClick(View v) {
        UserManager um = new UserManager();
        um.logout();
        finish();
    }

    /**
     * Logs user out if back button is pressed
     */
    public void onBackPressed() {
        UserManager um = new UserManager();
        um.logout();
        super.onBackPressed();
    }

    /**
     * Starts UserListActivity
     * @param v Button clicked
     */
    public void onUsersButtonClick(View v) {
        Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
    }

    /**
     * Starts EditProfile Activity
     * @param v Button clicked
     */
    public void onUserEditButtonClick(View v) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Starts ChangePasswordActivity
     * @param v Button clicked
     */
    public void onChangePassButtonClick(View v) {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    /**
     * Starts UserProfileActivity
     * @param v Button clicked
     */
    public void onViewProfileButtonClick(View v) {
        UserManager um = new UserManager();
        String user = um.getUserName();
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("user_object", user);
        startActivity(intent);
    }

}
