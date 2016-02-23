package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserManager um = new UserManager();
        um.addUser("user1", "pass1", "first1", "last1", "email1@test.com", "major1");
        um.addUser("u1", "p1", "f1", "l1", "e1@test.com", "m1");
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

    //yedukp76ffytfuy24zsqk7f5
    public void onRTButtonClick(View v) throws Exception {
        Log.d("**MOVIEMATCHER**", "RT Clicked");
        String req = "http://api.rottentomatoes.com/api/public/v1.0.json?apikey=yedukp76ffytfuy24zsqk7f5";
        String s;
        Log.d("**MOVIEMATCHER**", "Launching Thread");
        Thread thread = new Thread(new Runnable() {
            private String req;
            @Override
            public void run() {
                try {
                    Log.d("**MOVIEMATCHER**", "Requesting HTTP");
                    URL url = new URL(req);
                    InputStream is = url.openStream();
                    StringBuilder sb = new StringBuilder();
                    int ch;
                    while ((ch = is.read()) != -1) sb.append((char) ch);
                    Log.d("**MOVIEMATCHER**", sb.toString());
                } catch (Exception e) {
                    Log.d("**MOVIEMATCHER**", e.toString());
                    return;
                }
            }
            public Runnable init(String var) {
                Log.d("**MOVIEMATCHER**", "Successful init");
                req = var;
                return this;
            }
        }.init(req));
        thread.start();
        Log.d("**MOVIEMATCHER**", "Finished RT Request");
    }

}
