package com.skytalkers.app.moviematcher.controllers;

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
        ((EditText) findViewById(R.id.nameEditText)).setText(um.getUserName());
        ((EditText) findViewById(R.id.firstEditText)).setText(um.getUserFirst());
        ((EditText) findViewById(R.id.lastEditText)).setText(um.getUserLast());
        ((EditText) findViewById(R.id.emailEditText)).setText(um.getUserEmail());
    }

    public void onBackButtonClick(View v) {
        finish();
    }

    public void onApplyButtonClick(View v) {
        UserManager um = new UserManager();
        User user = um.getUser();
        String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String first = ((EditText) findViewById(R.id.firstEditText)).getText().toString();
        String last = ((EditText) findViewById(R.id.lastEditText)).getText().toString();
        um.editUser(name, first, last, email);
        finish();
    }

}
