package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruce on 2/28/2016.
 * Class to hold and manage a list of movies.
 */
public class MovieManager {
    private static Map<String,Movie> movieMap = new HashMap<>();
    private static ArrayList<Movie> movies;
    private static String req;
    private static ArrayList<Movie> userMovies = new ArrayList<>();

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
        for (Movie m : movies) {if (!movieMap.containsKey(m.getTitle())) movieMap.put(m.getTitle(), m);}
    }

    public void sendNewMovieRequest() throws Exception {
        movies = RottenTomatoesManager.getNewMovies();
        Log.d("**MOVIEMATCHER**", String.valueOf(movies.size()));
        for (Movie m : movies) {if (!movieMap.containsKey(m.getTitle())) movieMap.put(m.getTitle(), m);}
    }

    public void sendRecentDVDRequest() throws Exception {
        movies = RottenTomatoesManager.getRecentDVDs();
        for (Movie m : movies) {if (!movieMap.containsKey(m.getTitle())) movieMap.put(m.getTitle(), m);}
    }

    public ArrayList<Movie> getMovies() { return movies; }

    public Movie getMovie(String m) { return movieMap.get(m); }

    public Map<String,Movie> getMap() { return movieMap; }

    public Map<String,Integer> getRatings(String m) { return getMovie(m).getRatings(); }

    public Movie findMovie(String m) { return movieMap.get(m); }

    public void addMovie(String m) {
        userMovies.add(movieMap.get(m));
    }

    public void rate(String m, String u, int r) {
        movieMap.get(m).rate(u,r);
    }

    public ArrayList<Movie> getUserMovies() { return userMovies; }

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
