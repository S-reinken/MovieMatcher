package com.skytalkers.app.moviematcher.controllers.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.User;
import com.skytalkers.app.moviematcher.models.UserManager;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UserManager um = new UserManager();
        ((EditText) findViewById(R.id.nameEditText)).setText(um.getUserName());
        ((EditText) findViewById(R.id.firstEditText)).setText(um.getUserFirst());
        ((EditText) findViewById(R.id.lastEditText)).setText(um.getUserLast());
        ((EditText) findViewById(R.id.emailEditText)).setText(um.getUserEmail());
        ((EditText) findViewById(R.id.majorEditText)).setText(um.getUserMajor());
    }

    public void onBackButtonClick(View v) {
        finish();
    }

    public void onApplyButtonClick(View v) {
        UserManager um = new UserManager();
        String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String first = ((EditText) findViewById(R.id.firstEditText)).getText().toString();
        String last = ((EditText) findViewById(R.id.lastEditText)).getText().toString();
        String major = ((EditText) findViewById(R.id.majorEditText)).getText().toString();
        um.editUser(name, first, last, email, major);
        finish();
    }

}
