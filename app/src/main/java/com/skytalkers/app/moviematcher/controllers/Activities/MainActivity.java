package com.skytalkers.app.moviematcher.controllers.Activities;

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
        um.addUser("admin", "admin", "admin", "admin", "admin");
        um.addUser("1","2","3","4","5");
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

    //yedukp76ffytfuy24zsqk7f5
    public void onRTButtonClick(View v) throws Exception {
        Log.d("**MOVIEMATCHER**", "RT Clicked");
        String req = "http://api.rottentomatoes.com/api/public/v1.0.json?apikey=yedukp76ffytfuy24zsqk7f5";
        MovieManager mm = new MovieManager();
        mm.sendNewMovieRequest();
        Log.d("**MOVIEMATCHER**", mm.getTitles().get(0));
    }

    public void onDebugButtonClick(View v) {
        UserManager um = new UserManager();
        um.setUser("admin");
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }
}
