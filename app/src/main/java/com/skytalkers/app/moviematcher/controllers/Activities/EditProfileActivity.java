package com.skytalkers.app.moviematcher.controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.UserManager;

public class EditProfileActivity extends AppCompatActivity {

    /**
     * Occurs on creation of activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final UserManager um = new UserManager();
        ((EditText) findViewById(R.id.nameEditText)).setText(um.getUserName());
        ((EditText) findViewById(R.id.firstEditText)).setText(um.getUserFirst());
        ((EditText) findViewById(R.id.lastEditText)).setText(um.getUserLast());
        ((EditText) findViewById(R.id.emailEditText)).setText(um.getUserEmail());
        ((EditText) findViewById(R.id.majorEditText)).setText(um.getUserMajor());
    }

    /**
     * Returns to previous view if back button is clicked
     * @param v Button clicked
     */
    public void onBackButtonClick(View v) {
        finish();
    }

    /**
     * Applies changes to user profile
     * @param v
     */
    public void onApplyButtonClick(View v) {
        final UserManager um = new UserManager();
        final String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        final String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        final String first = ((EditText) findViewById(R.id.firstEditText)).getText().toString();
        final String last = ((EditText) findViewById(R.id.lastEditText)).getText().toString();
        final String major = ((EditText) findViewById(R.id.majorEditText)).getText().toString();
        um.editUser(name, first, last, email, major);
        finish();
    }

}
