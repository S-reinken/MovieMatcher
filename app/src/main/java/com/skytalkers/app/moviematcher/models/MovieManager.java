package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Bruce on 2/28/2016.
 */
public class MovieManager {
    private ArrayList<Movie> movies;
    private String req;

    public MovieManager() {

    }

    public MovieManager(String url) {
        req = url;
    }

    public void sendRTRequest(String name) throws InterruptedException {
        req = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey="
                + HTTPRequest.getKey()
                + "q="
                + name + "&page_limit=5";
        movies = RottenTomatoesManager.getRTRequest(req);
    }

    public void sendNewMovieRequest() throws InterruptedException {
        movies = RottenTomatoesManager.getNewMovies();
    }

    public void sendRecentDVDRequest() throws InterruptedException {
        movies = RottenTomatoesManager.getRecentDVDs();
    }

    public ArrayList<String> getTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Movie m : movies) titles.add(m.getTitle());
        return titles;
    }

    public ArrayList<Bitmap> getImages() {
        ArrayList<Bitmap> images = new ArrayList<>();
        for (Movie m : movies) images.add(m.getImage());
        return images;
    }
}
