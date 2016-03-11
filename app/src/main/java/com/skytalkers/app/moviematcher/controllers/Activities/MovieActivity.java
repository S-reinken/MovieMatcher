package com.skytalkers.app.moviematcher.controllers.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.Movie;
import com.skytalkers.app.moviematcher.models.MovieManager;
import com.skytalkers.app.moviematcher.models.UserManager;

import java.util.ArrayList;
import java.util.Map;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title = getIntent().getStringExtra("title");
        Bitmap image = getIntent().getParcelableExtra("image");
        MovieManager mm = new MovieManager();
        ((TextView) findViewById(R.id.movieTextView)).setText(title);
        ((ImageView) findViewById(R.id.movieImageView)).setImageBitmap(Bitmap.createScaledBitmap(image, 540, 800, false));
        ((RatingBar) findViewById(R.id.avgAllRating)).setRating(mm.getMovie(title).getAverageRating());
        ((RatingBar) findViewById(R.id.avgMajorRating)).setRating(mm.getMovie(title).getMajorRating());
        try {
            int rating = mm.getRatings(title).get(new UserManager().getUserName());
            ((RatingBar) findViewById(R.id.userRating)).setRating(mm.getRatings(title).get(new UserManager().getUserName()));
        } catch (Exception e) { };
        ((RatingBar) findViewById(R.id.userRating)).setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                UserManager um = new UserManager();
                String title = MovieActivity.this.getIntent().getStringExtra("title");
                um.rate(title, (int) rating);
                MovieManager mm = new MovieManager();
                mm.rate(title, um.getUserName(), (int) rating);
            }
        });
    }





}
