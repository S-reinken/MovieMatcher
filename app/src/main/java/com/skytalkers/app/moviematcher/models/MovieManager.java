package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private static String listTitle;


    public MovieManager() {

    }


    public MovieManager(String url) {
        req = url;
    }

    public void setTitle(String title) {
        listTitle = title;
    }

    public String getTitle() { return listTitle; }

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
        Log.d("Movie Add", m + " " + String.valueOf(movieMap.get(m)));
        userMovies.add(movieMap.get(m));
        DatabaseManager mgr = new DatabaseManager();
        mgr.addMovie(movieMap.get(m));
    }

    public void rate(String m, String u, int r) {
        movieMap.get(m).rate(u, r);
    }

    public ArrayList<Movie> getUserMovies() { return userMovies; }

    public ArrayList<String> getTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Movie m : movies) titles.add(m.getTitle());
        return titles;
    }

    public ArrayList<String> getUserTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Movie m : userMovies) titles.add(m.getTitle());
        return titles;
    }

    public boolean contains(Movie mov) {
        for (Movie m : userMovies) if (m.equals(mov)) return true;
        return false;
    }

    public void getOverallRec() {
        Collections.sort(userMovies, new Comparator<Movie>() {
           public int compare(Movie m1, Movie m2) {
               return m2.getAverageRating() - m1.getAverageRating();
           }
        });
        movies = userMovies;
    }

    public ArrayList<Movie> filterMajorMovies() {
        ArrayList<Movie> temp = new ArrayList<>();
        for (Movie m : userMovies) {
            if (m.getMajorRating() != 0) temp.add(m);
        }
        return temp;
    }

    public void getMajorRec(String major) {
        Collections.sort(userMovies, new Comparator<Movie>() {
            public int compare(Movie m1, Movie m2) {
                return m2.getMajorRating() - m1.getMajorRating();
            }
        });
        movies = filterMajorMovies();
    }
}
