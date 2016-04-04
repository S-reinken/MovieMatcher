package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.skytalkers.app.moviematcher.models.UserManager;
import com.skytalkers.app.moviematcher.R;

public class RegisterScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Completes registration and finishes activity if user does not already exist
     * @param v Button that was clicked
     */
    public void onRegisterButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Attempting Registration");
        final UserManager um = new UserManager();
        final String name = ((EditText) findViewById(R.id.regUsername)).getText().toString();
        final String email = ((EditText) findViewById(R.id.regEmail)).getText().toString();
        final String first = ((EditText) findViewById(R.id.regFirst)).getText().toString();
        final String last = ((EditText) findViewById(R.id.regLast)).getText().toString();
        final String pass = ((EditText) findViewById(R.id.regPassword)).getText().toString();
        final Context context = getApplicationContext();
        final int dur = Toast.LENGTH_SHORT;
        if (um.findUser(name) != null) {
            final Toast t = Toast.makeText(context, "Username already exists", dur);
            t.show();
        } else {
            um.addUser(name, pass, first, last, email);
            final Toast t = Toast.makeText(context, "User successfully registered", dur);
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
