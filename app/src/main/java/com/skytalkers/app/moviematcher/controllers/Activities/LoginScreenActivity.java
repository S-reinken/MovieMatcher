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
import com.skytalkers.app.moviematcher.models.MovieManager;
import com.skytalkers.app.moviematcher.models.ToastWrapper;
import com.skytalkers.app.moviematcher.models.UserManager;

public class LoginScreenActivity extends AppCompatActivity {
    String MMTag = "**MOVIEMATCHER**";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        UserManager um = new UserManager();
        MovieManager mm = new MovieManager();

        setContentView(R.layout.activity_login_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Pauses activity
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d(MMTag, "Pausing the login screen");
    }

    /**
     * Resumes activity
     */
    public void onResume() {
        super.onResume();
        Log.d(MMTag, "Resuming the login screen");
    }

    /**
     * Attempts login
     * @param v Button clicked
     */
    public void onLoginButtonClick(View v) {
        Log.d(MMTag, "Attempting Login");
        UserManager um = new UserManager();
        String name = ((EditText) findViewById(R.id.usernameText)).getText().toString();
        String pass = ((EditText) findViewById(R.id.passwordText)).getText().toString();
        if (um.login(name, pass)) {
            Log.d(MMTag, "Login Match");
            if (!um.isBanned(name)) {
                um.setUser(name);
                Intent intent = new Intent(this, NavigationActivity.class);
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

    /**
     * Cancels login
     * @param v Button clicked
     */
    public void onCancelButtonClick(View v) {
        Log.d(MMTag, "Cancel Login");
        finish();
    }
}
