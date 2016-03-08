package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.ToastWrapper;
import com.skytalkers.app.moviematcher.models.DatabaseManager;
import com.skytalkers.app.moviematcher.models.UserManager;

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        DatabaseManager mgr = new DatabaseManager();
        mgr.prepareUsers();


        setContentView(R.layout.activity_login_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    public void prepareDatabase() {

    }
    public void onLoginButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Attempting Login");
        UserManager um = new UserManager();
        String name = ((EditText) findViewById(R.id.usernameText)).getText().toString();
        String pass = ((EditText) findViewById(R.id.passwordText)).getText().toString();
        if (um.login(name, pass)) {
            Log.d("**MOVIEMATCHER**", "Login Success");
            um.setUser(name);
            Intent intent = new Intent(this, NavigationActivity.class);
            startActivity(intent);
            finish();
        } else {
            Context context = this;
            int dur = Toast.LENGTH_SHORT;
            Log.d("**MOVIEMATCHER**", "Login Failed");
            Toast t = Toast.makeText(context, "Login Failed", dur);
            t.show();
        }
        System.out.println("Now the button click has ended");
    }

    public void onCancelButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Cancel Login");
        finish();
    }
}
