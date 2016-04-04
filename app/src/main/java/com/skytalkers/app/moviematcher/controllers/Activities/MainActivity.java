package com.skytalkers.app.moviematcher.controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.client.Firebase;
import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.DatabaseManager;
import com.skytalkers.app.moviematcher.models.Movie;
import com.skytalkers.app.moviematcher.models.MovieManager;
import com.skytalkers.app.moviematcher.models.ToastWrapper;
import com.skytalkers.app.moviematcher.models.UserManager;


public class MainActivity extends AppCompatActivity {

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
        Log.d("**MOVIEMATCHER**", "Pausing the main opening screen");
    }

    public void onResume() {
        super.onResume();
        Log.d("**MOVIEMATCHER**", "Resuming the main opening screen");
    }

    public void onLoginButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Login button clicked");
        final Intent intent = new Intent(this, LoginScreenActivity.class);
        startActivity(intent);
    }

    public void onRegisterButtonClick(View v) {
        Log.d("**MOVIEMATCHER**", "Register button clicked");
        final Intent intent = new Intent(this, RegisterScreenActivity.class);
        startActivity(intent);
    }

    //yedukp76ffytfuy24zsqk7f5
    public void onRTButtonClick(View v) throws Exception {
        Log.d("**MOVIEMATCHER**", "RT Clicked");
        //final String req = "http://api.rottentomatoes.com/api/public/v1.0.json?apikey=yedukp76ffytfuy24zsqk7f5";
        final MovieManager mm = new MovieManager();
        //try {
        mm.sendNewMovieRequest();
        //} catch (Exception e) { for (Movie m : mm.getMovies()) Log.d("**MOVIEMATCHER**", "Title" + m.getTitle()); }
        Log.d("**MOVIEMATCHER**", mm.getTitles().get(0));
    }

    public void onDebugButtonClick(View v) {

        final UserManager um = new UserManager();
        //um.databaseTest();
        um.setUser("admin");
        final Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }

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

    public void majorRecTesting() {
        final MovieManager mm = new MovieManager();
        try { mm.sendNewMovieRequest(); } catch (Exception e) {
            Log.d("**MOVIEMATCHER**", "Whoops, something went wrong.");
            ToastWrapper.show(this, "Failed to get movies");
        }
        int rating = 1;
        final int LISTLENGTH = 5;
        for (int i = 0; i < LISTLENGTH; i+=2) {
            final Movie m = mm.getMovies().get(i);
            m.rate("admin", rating++);
            mm.addMovie((m.getTitle()));
        }
        Log.d("MOVIEMATCHER", String.valueOf(mm.getUserMovies().size()));
    }

}
