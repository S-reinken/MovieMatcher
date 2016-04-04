package com.skytalkers.app.moviematcher.models;

import android.util.Log;

//import com.android.volley.*;
////import com.android.volley.Request;
////import com.android.volley.Response;
////import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//yedukp76ffytfuy24zsqk7f5
//"http://api.rottentomatoes.com/api/public/v1.0.json?apikey=yedukp76ffytfuy24zsqk7f5"
final public class RottenTomatoesManager {

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
     * @throws Exception if there is an error fetching movies
     */
    public static List<Movie> getNewMovies() throws Exception { //Opening?; only returning up to 5 for now
        final String req = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=yedukp76ffytfuy24zsqk7f5&page_limit=5";
        return getRTRequest(req);
    }


    /**
     * Fetches a list of Movie objects that are new on DVD
     * @return ArrayList of new DVDs
     * @throws Exception if there is an error fetching movies
     */
    public static List<Movie> getRecentDVDs() throws Exception {
        final String req = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=yedukp76ffytfuy24zsqk7f5&page_limit=5";
        return getRTRequest(req);
    }

    /**
     * Sends a request to Rotten Tomatoes to fetch a list of movies and parses
     * JSON files into Movie objects
     * @param url The url to request from RT
     * @return ArrayList of movies
     * @throws Exception if there is an error fetching movies or parsing JSONs
     */
    public static List<Movie> getRTRequest(String url) throws Exception {
        final ArrayList<Movie> movies = new ArrayList<>();
        String res;
        try {
            final HTTPRequest http = new HTTPRequest(url);
            http.sendRequest();
            res = http.getResponse();
        } catch (InterruptedException e) {
            Log.d("**JSON**", "EXCEPTION: " + e.toString());
            return movies;
        }

        JSONObject json = null;
        try {
            json = new JSONObject(res);
        } catch (JSONException e) {
            Log.d("**JSON**", "Failed to get JSON object");
            //e.printStackTrace();
        }
        assert json != null;
        //now get the array of "movies"data
        final JSONArray array = json.optJSONArray("movies");
        for (int i = 0; i < array.length(); i++) {
            try {
                final JSONObject jsonObject = array.getJSONObject(i);
                assert jsonObject != null;
                final String title = jsonObject.optString("title");
                final int id = jsonObject.optInt("id");
                final HTTPRequest http = new HTTPRequest(jsonObject.getJSONObject("posters").optString("thumbnail"));
                http.sendImageRequest();
                final String image = http.getImage();
                final Movie m = new Movie(title, id, image);
                movies.add(m);
            } catch (Exception e) {
                Log.d("VolleyApp", "Failed to get JSON object");
                //e.printStackTrace();
            }
        }
        return movies;


    }


}