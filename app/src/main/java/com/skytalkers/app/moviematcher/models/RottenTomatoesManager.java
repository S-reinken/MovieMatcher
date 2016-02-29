package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    private String res;
    //getMoviebyName(String name) {
    //getMoviebyDate(Date initial, Date final)

    public static ArrayList<Movie> getNewMovies() throws Exception { //Opening?; only returning up to 5 for now
        String req = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=yedukp76ffytfuy24zsqk7f5&page_limit=5";
        return getRTRequest(req);
    }

    public static ArrayList<Movie> getRecentDVDs() throws Exception {
        String req = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=yedukp76ffytfuy24zsqk7f5&page_limit=5";
        return getRTRequest(req);
    }

    public static ArrayList<Movie> getRTRequest(String url) throws Exception {
        ArrayList<Movie> movies = new ArrayList<>();
        String res;
        try {
            HTTPRequest http = new HTTPRequest(url);
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
            e.printStackTrace();
        }
        assert json != null;
        //now get the array of "movies"data
        JSONArray array = json.optJSONArray("movies");
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject jsonObject = array.getJSONObject(i);
                assert jsonObject != null;
                String title = jsonObject.optString("title");
                int id = jsonObject.optInt("id");
                HTTPRequest http = new HTTPRequest(jsonObject.getJSONObject("posters").optString("thumbnail"));
                http.sendImageRequest();
                Bitmap image = http.getImage();
                Movie m = new Movie(title, id, image);
                movies.add(m);
            } catch (Exception e) {
                Log.d("VolleyApp", "Failed to get JSON object");
                e.printStackTrace();
            }
        }
        return movies;


    }


}