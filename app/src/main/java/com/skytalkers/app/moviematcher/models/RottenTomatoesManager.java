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

//yedukp76ffytfuy24zsqk7f5
//"http://api.rottentomatoes.com/api/public/v1.0.json?apikey=yedukp76ffytfuy24zsqk7f5"
public class RottenTomatoesManager {

    //getMoviebyName(String name) {
    //getMoviebyDate(Date initial, Date final)

    public static ArrayList<Movie> getNewMovies() { //Opening?; only returning up to 5 for now
        String req = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/opening.json?apikey=yedukp76ffytfuy24zsqk7f5&limit=5";
        return getRTRequest(req);
    }

//    public static ArrayList<Movie> getRecentDVDs() {
//        String req = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=yedukp76ffytfuy24zsqk7f5";
//        ArrayList<Movie> recentDvd = getRTRequest(req);
//        return movies;
//    }

    private static ArrayList<Movie> getRTRequest(String url) {
        ArrayList<Movie> movies = new ArrayList<>();
        String res;
        try {
             res = HTTPRequest.sendRequest(url);
        } catch (InterruptedException e) {
            Log.d("**JSON**", "EXCEPTION: " + e.toString());
            return movies;
        }

        JSONObject json = null;
        try {
             json = new JSONObject(res);
        } catch (JSONException e) {
            Log.d("**JSON**", "Failed to get JSON object");
            e.printStackTrace();
        }
        assert json != null;
        //now get the array of "movies"data
        JSONArray array = json.optJSONArray("movies");
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject jsonObject = array.getJSONObject(i);
                Movie m = new Movie();
                assert jsonObject != null;
                m.setTitle((jsonObject.optString("title")));
                movies.add(m);
            } catch (JSONException e) {
                Log.d("VolleyApp", "Failed to get JSON object");
                e.printStackTrace();
            }
        }
        return movies;


    }


}