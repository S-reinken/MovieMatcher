package com.skytalkers.app.moviematcher.controllers.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.skytalkers.app.moviematcher.R;
import com.skytalkers.app.moviematcher.models.Movie;
import com.skytalkers.app.moviematcher.models.MovieManager;
import com.skytalkers.app.moviematcher.models.UserManager;


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
        Bitmap image = decodeImage(getIntent().getStringExtra("image"));
        MovieManager mm = new MovieManager();
        Log.d("MovieActivity", title);
        for (Movie m : mm.getMovies()) {
            Log.d("MovieActivity", m.getTitle());
            Log.d("MovieActivity", mm.getMovie(m.getTitle()).getTitle());
        }
        ((TextView) findViewById(R.id.movieTextView)).setText(title.replace('_','.'));
        ((ImageView) findViewById(R.id.movieImageView)).setImageBitmap(Bitmap.createScaledBitmap(image, 405, 600, false));
        ((RatingBar) findViewById(R.id.avgAllRating)).setRating(mm.getMovie(title).getAverageRating());
        ((RatingBar) findViewById(R.id.avgMajorRating)).setRating(mm.getMovie(title).getMajorRating());
        try {
            int rating = mm.getRatings(title).get(new UserManager().getUserName());
            ((RatingBar) findViewById(R.id.userRating)).setRating(mm.getRatings(title).get(new UserManager().getUserName()));
        } catch (Exception e) { }
        ((RatingBar) findViewById(R.id.userRating)).setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                UserManager um = new UserManager();
                String title = MovieActivity.this.getIntent().getStringExtra("title");
                //um.rate(title, (int) rating);
                MovieManager mm = new MovieManager();
                mm.rate(title, um.getUserName(), (int) rating);
            }
        });
    }

    /**
     * Decodes image into bitmap
     * @param image Image to be decoded
     * @return final bitmap
     */
    public Bitmap decodeImage(String image) {
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }



}
