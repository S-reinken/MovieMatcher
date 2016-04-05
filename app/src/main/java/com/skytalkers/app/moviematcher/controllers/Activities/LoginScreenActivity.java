package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.ToastWrapper;
import com.skytalkers.app.moviematcher.models.UserManager;

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_login_screen);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        final UserManager um = new UserManager();
        final String name = ((EditText) findViewById(R.id.usernameText)).getText().toString();
        final String pass = ((EditText) findViewById(R.id.passwordText)).getText().toString();
        if (um.login(name, pass)) {
            Log.d("**MOVIEMATCHER**", "Login Match");
            if (!um.isBanned(name)) {
                um.setUser(name);
                final Intent intent = new Intent(this, NavigationActivity.class);
                startActivity(intent);
                finish();
            } else {
                ToastWrapper.show(this, "User Banned");
            }
        } else {
            ToastWrapper.show(this, "Login Failed");
        }
        //System.out.println("Now the button click has ended");
    }

    public void onCancelButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Cancel Login");
        finish();
    }
}
