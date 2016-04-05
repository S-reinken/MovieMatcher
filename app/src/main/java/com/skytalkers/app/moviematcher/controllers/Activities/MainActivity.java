package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.client.Firebase;
import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.*;


public class MainActivity extends AppCompatActivity {

    /**
     * Occurs on creation of activity
     * @param savedInstanceState
     */
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

    /**
     * Occurs on pause, writes log
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d("**MOVIEMATCHER**", "Pausing the main opening screen");
    }

    /**
     * Occurs on resume, writes log
     */
    public void onResume() {
        super.onResume();
        Log.d("**MOVIEMATCHER**", "Resuming the main opening screen");
    }

    /**
     * Starts login screen activity when login button is clicked
     * @param v Button that was clicked
     */
    public void onLoginButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Login button clicked");
        final Intent intent = new Intent(this, LoginScreenActivity.class);
        startActivity(intent);
    }

    /**
     * Starts registration activity when register button is clicked
     * @param v Button that was clicked
     */
    public void onRegisterButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Register button clicked");
        final Intent intent = new Intent(this, RegisterScreenActivity.class);
        startActivity(intent);
    }

    /**
     * Sends request for new movies to RottenTomatoes
     * @param v Button that was clicked
     * @throws Exception InterruptedException, JSONException, Exception
     */
    //yedukp76ffytfuy24zsqk7f5
    public void onRTButtonClick(View v) throws Exception {
        Log.d("**MOVIEMATCHER**", "RT Clicked");
        final String req = "http://api.rottentomatoes.com/api/public/v1.0.json?apikey=yedukp76ffytfuy24zsqk7f5";
        final MovieManager mm = new MovieManager();
        //try {
        mm.sendNewMovieRequest();
        //} catch (Exception e) { for (Movie m : mm.getMovies()) Log.d("**MOVIEMATCHER**", "Title" + m.getTitle()); }
        Log.d("**MOVIEMATCHER**", mm.getTitles().get(0));
    }

    /**
     * Enables admin features
     * @param v Button that was clicked
     */
    public void onDebugButtonClick(View v) {

        final UserManager um = new UserManager();
        //um.databaseTest();
        um.setUser("admin");
        final Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }

    /**
     * Tests ratings
     */
    public void recTesting() {
        final MovieManager mm = new MovieManager();
        try { mm.sendNewMovieRequest(); } catch (Exception e) {
            Log.d("**MOVIEMATCHER**", "Whoops, something went wrong.");
            ToastWrapper.show(this, "Failed to get movies");
        }
        int rating = 1;
        for (final Movie m : mm.getMovies()) {
            m.rate("admin", rating++);
            mm.addMovie(m.getTitle());
        }
    }

    /**
     * Tests major specific movie ratings
     */
    public void majorRecTesting() {
        final MovieManager mm = new MovieManager();
        try { mm.sendNewMovieRequest(); } catch (Exception e) {
            Log.d("**MOVIEMATCHER**", "Whoops, something went wrong.");
            ToastWrapper.show(this, "Failed to get movies");
        }
        int rating = 1;
        for (int i = 0; i < 5; i+=2) {
            final Movie m = mm.getMovies().get(i);
            m.rate("admin", rating++);
            mm.addMovie((m.getTitle()));
        }
        Log.d("MOVIEMATCHER", String.valueOf(mm.getUserMovies().size()));
    }

}
