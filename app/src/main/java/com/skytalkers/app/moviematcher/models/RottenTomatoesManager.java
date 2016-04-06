package com.skytalkers.app.moviematcher.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//yedukp76ffytfuy24zsqk7f5
//"http://api.rottentomatoes.com/api/public/v1.0.json?apikey=yedukp76ffytfuy24zsqk7f5"
public final class RottenTomatoesManager {

    //getMoviebyName(String name) {
    //getMoviebyDate(Date initial, Date final)

    /**
     * Constructs empty RottenTomatoesManager object
     */
    private RottenTomatoesManager() {

    }

    /**
     * Fetches a list of Movie objects that are currently in theaters
     * @return ArrayList of new movies
     */
    public static List<Movie> getNewMovies() { //Opening?; only returning up to 5 for now
        final String req = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=yedukp76ffytfuy24zsqk7f5&page_limit=5";
        return getRTRequest(req);
    }


    /**
     * Fetches a list of Movie objects that are new on DVD
     * @return ArrayList of new DVDs
     */
    public static List<Movie> getRecentDVDs() {
        final String req = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=yedukp76ffytfuy24zsqk7f5&page_limit=5";
        return getRTRequest(req);
    }

    /**
     * Sends a request to Rotten Tomatoes to fetch a list of movies and parses
     * JSON files into Movie objects
     * @param url The url to request from RT
     * @return ArrayList of movies
     */
    public static List<Movie> getRTRequest(String url) {
        final ArrayList<Movie> movies = new ArrayList<>();
        String res;
        final HTTPRequest http = new HTTPRequest(url);
        http.sendRequest();
        res = http.getResponse();

        JSONObject json = null;
        try {
            json = new JSONObject(res);
        } catch (JSONException e) {
            Log.d("**JSON**", "Failed to get JSON object");
        }
        assert json != null;
        //now get the array of "movies"data
        final JSONArray array = json.optJSONArray("movies");
        for (int i = 0; i < array.length(); i++) {
            try {
                final JSONObject jsonObject = array.getJSONObject(i);
                assert jsonObject != null;
                String title = jsonObject.optString("title");
                final int id = jsonObject.optInt("id");
                final HTTPRequest https = new HTTPRequest(jsonObject.getJSONObject("posters").optString("thumbnail"));
                https.sendImageRequest();
                final String image = https.getImage();
                title = title.replace('.','_');
                Log.d("RTManager", title);
                final Movie m = new Movie(title, id, image);
                movies.add(m);
            } catch (JSONException e) {
                Log.d("VolleyApp", "Failed to get JSON object");
            }
        }
        return movies;


    }


}