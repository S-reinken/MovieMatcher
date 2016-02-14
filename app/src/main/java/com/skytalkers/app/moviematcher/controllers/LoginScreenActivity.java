package com.skytalkers.app.moviematcher.controllers;

import android.content.Intent;
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

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
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

    @Override
    public void onPause() {
        super.onPause();
        Log.d("**MOVIEMATCHER**", "Pausing the login screen");
    }

    public void onResume() {
        super.onResume();
        Log.d("**MOVIEMATCHER**", "Resuming the login screen");
    }

    public void onLoginButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Attempting Login");
        UserManager um = new UserManager();
        String name = ((EditText) findViewById(R.id.usernameText)).getText().toString();
        String pass = ((EditText) findViewById(R.id.passwordText)).getText().toString();
        if (um.login(name, pass)) {
            Log.d("**MOVIEMATCHER**", "Login Success");
            um.setUser(name);
            Intent intent = new Intent(this, PrimaryActivity.class);
            startActivity(intent);
            finish();
        } else {
            Context context = getApplicationContext();
            int dur = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, "Login Failed", dur);
            t.show();
        }
    }

    public void onCancelButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Cancel Login");
        finish();
    }
}
