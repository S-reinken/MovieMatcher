package com.skytalkers.app.moviematcher.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bruce on 2/28/2016.
 * Class to hold and manage a list of movies.
 */
public class MovieManager {
    /**
     * Map of movie name to movie
     */
    private static Map<String,Movie> movieMap = new HashMap<>();
    /**
     * List of movies
     */
    private static List<Movie> movies;
    /**
     * List of personal Movies
     */
    private static List<Movie> userMovies;
    /**
     * Title of type type
     */
    private static String listTitle;
    /**
     * Type of list
     */
    private static int type;

    /**
     * Constructor for pulling movielist from database if not already
     */
    public MovieManager() {
        if (userMovies == null) {
            userMovies = new DatabaseManager().getMovieList();
            for (final Movie m : userMovies) {
                movieMap.put(m.getTitle(), m);
            }
        }
    }

    /**
     * Set Boolean for type of event
     * @param t type of event
     */
    public void setType(int t) { type = t; }

    /**
     * Get type of event
     * @return type of event
     */
    public int getType() { return type; }

    /**
     * Set list title
     * @param title title string
     */
    public void setTitle(String title) {
        listTitle = title;
    }

    /**
     * Get title string
     * @return title string
     */
    public String getTitle() { return listTitle; }

    /**
     * Send a request to rotten tomatoes
     * @param name parsed search
     */
    public void sendRTRequest(String name) {
        final String req = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey="
                + HTTPRequest.getKey()
                + "q="
                + name + "&page_limit=5";
        movies = RottenTomatoesManager.getRTRequest(req);
        for (final Movie m : movies) {
            if (!movieMap.containsKey(m.getTitle())) { movieMap.put(m.getTitle(), m); }
        }
    }

    /**
     * Send request for new movies
     */
    public void sendNewMovieRequest() {
        movies = RottenTomatoesManager.getNewMovies();
        Log.d("**MOVIEMATCHER**", String.valueOf(movies.size()));
        for (final Movie m : movies) {
            if (!movieMap.containsKey(m.getTitle())) { movieMap.put(m.getTitle(), m); }
        }
    }

    /**
     * Send request for new dvds
     */
    public void sendRecentDVDRequest() {
        movies = RottenTomatoesManager.getRecentDVDs();
        for (final Movie m : movies) {
            if (!movieMap.containsKey(m.getTitle())) { movieMap.put(m.getTitle(), m); }
        }
    }

    /**
     * Get list of movies
     * @return list of Movies
     */
    public List<Movie> getMovies() { return movies; }

    /**
     * Get a movie by name
     * @param m Movie name
     * @return Movie object
     */
    public Movie getMovie(String m) { return movieMap.get(m); }

    /**
     * Get ratings of a movie
     * @param m Movie name
     * @return Map of ratings by user
     */
    public Map<String,Integer> getRatings(String m) { return getMovie(m).getRatings(); }

    /**
     * Add movie to internal list
     * @param m Movie name
     */
    public void addMovie(String m) {
        Log.d("Movie Add", m);
        userMovies.add(movieMap.get(m));
        final DatabaseManager mgr = new DatabaseManager();
        Log.d("MovieManager", movieMap.get(m).toString());
        mgr.addMovie(movieMap.get(m));
    }

    /**
     * Rate a movie
     * @param m movie name to rate
     * @param u User rating it
     * @param r Rating value
     */
    public void rate(String m, String u, int r) {
        movieMap.get(m).rate(u, r);
        new DatabaseManager().rate(movieMap.get(m), u, r);
    }

    /**
     * Get list of titles of currnet movie list
     * @return List of titles
     */
    public List<String> getTitles() {
        final ArrayList<String> titles = new ArrayList<>();
        for (final Movie m : movies) {
            titles.add(m.getTitle().replace('_','.'));
        }
        return titles;
    }

    /**
     * Check for a movie
     * @param mov Movie to check for
     * @return true is movie is in list, else false
     */
    public boolean contains(Movie mov) {
        for (final Movie m : userMovies) {
            if (m.equals(mov)) { return true; }
        }
        return false;
    }

    /**
     * Get list of movies sorted by overall rating
     */
    public void getOverallRec() {
        Collections.sort(userMovies, new Comparator<Movie>() {
            public int compare(Movie m1, Movie m2) {
                return m2.getAverageRating() - m1.getAverageRating();
            }
        });
        movies = userMovies;
    }

    /**
     * Get list of movie that have been rated by a major
     * @return list of movies filtered by a major
     */
    public List<Movie> filterMajorMovies() {
        final ArrayList<Movie> temp = new ArrayList<>();
        for (final Movie m : userMovies) {
            if (m.getMajorRating() != 0) { temp.add(m); }
        }
        return temp;
    }

    /**
     * Get a list of major rated movies sorted by rating
     * @param major list of movies by rating
     */
    public void getMajorRec(String major) {
        Collections.sort(userMovies, new Comparator<Movie>() {
            public int compare(Movie m1, Movie m2) {
                return m2.getMajorRating() - m1.getMajorRating();
            }
        });
        movies = filterMajorMovies();
    }
}
