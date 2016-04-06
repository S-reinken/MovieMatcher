package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.client.Firebase;
import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.DatabaseManager;


public class MainActivity extends AppCompatActivity {
    /**
     * App tag
     */
    private static String mmtag = "**MOVIEMATCHER**";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        final DatabaseManager mgr = new DatabaseManager();
        mgr.prepareUsers();
        setContentView(R.layout.activity_main);

        //recTesting();
        //majorRecTesting();
        //databaseTest();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(mmtag, "Pausing the main opening screen");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(mmtag, "Resuming the main opening screen");
    }

    /**
     * Start login activity
     * @param v Current View
     */
    public void onLoginButtonClick(View v) {
        Log.d(mmtag, "Login button clicked");
        final Intent intent = new Intent(this, LoginScreenActivity.class);
        startActivity(intent);
    }

    /**
     * Start register activity
     * @param v Current View
     */
    public void onRegisterButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Register button clicked");
        final Intent intent = new Intent(this, RegisterScreenActivity.class);
        startActivity(intent);
    }
/*
    public void onDebugButtonClick(View v) {

        final UserManager um = new UserManager();
        //um.databaseTest();
        um.setUser("admin");
        final Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }
*/
}
