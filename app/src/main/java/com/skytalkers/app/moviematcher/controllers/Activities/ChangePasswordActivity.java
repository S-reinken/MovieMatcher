package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.UserManager;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Finishes activity on back press.
     * @param v Current View
     */
    public void onBackButtonClick(View v) {
        finish();
    }

    /**
     * Confirms password change
     * @param v Current View
     */
    public void onApplyButtonClick(View v) {
        final UserManager um = new UserManager();
        final String name = um.getUserName();
        String pass = ((EditText) findViewById(R.id.oldEdit)).getText().toString();
        if (!confirm(name, pass)) { return; }
        pass = ((EditText) findViewById(R.id.confirmOldEdit)).getText().toString();
        if (!confirm(name, pass)) { return; }
        pass = ((EditText) findViewById(R.id.newEdit)).getText().toString();
        if (!pass.equals(((EditText) findViewById(R.id.confirmNewEdit)).getText().toString())) {
            msgbox("New passwords don't match");
        } else {
            um.changePass(pass);
            finish();
        }
    }

    /**
     * Confirm correct password
     * @param name Username
     * @param pass password
     * @return return true on correct password, else false.
     */
    public boolean confirm(String name, String pass) {
        final UserManager um = new UserManager();
        if (!um.login(name, pass)) {
            msgbox("Incorrect Password");
            return false;
        }
        return true;
    }

    /**
     * Method to display alert.
     * @param msg Message to display
     */
    public void msgbox(String msg) {
        final Context context = getApplicationContext();
        final int dur = Toast.LENGTH_SHORT;
        final Toast t = Toast.makeText(context, msg, dur);
        t.show();
    }

}
