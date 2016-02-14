package com.skytalkers.app.moviematcher.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.UserManager;

public class RegisterScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
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
    }

    public void onRegisterButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Attempting Registration");
        UserManager um = new UserManager();
        String name = ((EditText) findViewById(R.id.regUsername)).getText().toString();
        String email = ((EditText) findViewById(R.id.regEmail)).getText().toString();
        String first = ((EditText) findViewById(R.id.regFirst)).getText().toString();
        String last = ((EditText) findViewById(R.id.regLast)).getText().toString();
        String pass = ((EditText) findViewById(R.id.regPassword)).getText().toString();
        Context context = getApplicationContext();
        int dur = Toast.LENGTH_SHORT;
        if (um.findUser(name) != null) {
            Toast t = Toast.makeText(context, "Username already exists", dur);
            t.show();
        } else {
            um.addUser(name, pass, first, last, email);
            Toast t = Toast.makeText(context, "User successfully registered", dur);
            t.show();
            finish();
        }
    }

    public void onCancelButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Cancelling Registration");
        finish();
    }

}
