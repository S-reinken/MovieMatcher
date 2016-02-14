package com.skytalkers.app.moviematcher.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserManager um = new UserManager();
        um.addUser("user1", "pass1", "first1", "last1", "email1@test.com");
        um.addUser("u1", "p1", "f1", "l1", "e1@test.com");
        //um.addUser("user", "pass");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("**MOVIEMATCHER**", "Pausing the main opening screen");
    }

    public void onResume() {
        super.onResume();
        Log.d("**MOVIEMATCHER**", "Resuming the main opening screen");
    }

    public void onLoginButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Login button clicked");
        Intent intent = new Intent(this, LoginScreenActivity.class);
        startActivity(intent);
    }

    public void onRegisterButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Register button clicked");
        Intent intent = new Intent(this, RegisterScreenActivity.class);
        startActivity(intent);
    }
}
