package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.skytalkers.app.moviematcher.models.MovieManager;
import com.skytalkers.app.moviematcher.models.UserManager;
import com.skytalkers.app.moviematcher.R;

public class RegisterScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UserManager um = new UserManager();
        MovieManager mm = new MovieManager();
    }

    /**
     * Completes registration and finishes activity if user does not already exist
     * @param v Button that was clicked
     */
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

    /**
     * Ends activity
     * @param v Button that was clicked
     */
    public void onCancelButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Cancelling Registration");
        finish();
    }

}
