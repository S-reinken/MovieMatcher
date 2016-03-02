package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Movie {
    private String name;
    private int id;
    private Bitmap image;
    private Map<String,Integer> ratings = new HashMap<String,Integer>();

    //date released
    //ratinglist / array

    public Movie() {

    }

    public Movie(String t) { name= t; }

    public Movie(String t, int i, Bitmap b) {
        name = t;
        id = i;
        image = b;
    }

    public String getTitle() { return name; }

    public Bitmap getImage() { return image; }

    public int getId() { return id; }

    public void setTitle(String t) { name = t; }

    public void setImage(Bitmap b) { image = b; }

    public void rate(String u, int r) { ratings.put(u,r); }

    public int getRating(String u) { return ratings.get(u) == null ? 0 : ratings.get(u); }

    public Map<String,Integer> getRatings() { return ratings;   }

    public int getAverageRating() {
        int avg = 0;
        for (int r : ratings.values()) {
            avg += r;
        }
        return ratings.size() > 0 ? avg / ratings.size() : 0;
    }
}