package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Bruce on 2/28/2016.
 * Class to hold and manage a list of movies.
 */
public class MovieManager {
    private static ArrayList<Movie> movies;
    private static String req;

    public MovieManager() {

    }

    public MovieManager(String url) {
        req = url;
    }

    public void sendRTRequest(String name) throws Exception {
        req = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey="
                + HTTPRequest.getKey()
                + "q="
                + name + "&page_limit=5";
        movies = RottenTomatoesManager.getRTRequest(req);
    }

    public void sendNewMovieRequest() throws Exception {
        movies = RottenTomatoesManager.getNewMovies();
    }

    public void sendRecentDVDRequest() throws Exception {
        movies = RottenTomatoesManager.getRecentDVDs();
    }

    public ArrayList<Movie> getMovies() { return movies; }

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
