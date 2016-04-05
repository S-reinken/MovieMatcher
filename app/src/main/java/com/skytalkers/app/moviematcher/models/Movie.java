package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Map;

public class Movie {
    private String name;
    private int id;
    private String image;
    private Map<String,Integer> ratings = new HashMap<String,Integer>();

    //date released
    //ratinglist / array

    public Movie() {

    }

    public Movie(String t) { name= t; }

    public Movie(String t, int i, String b) {
        name = t;
        id = i;
        image = b;
    }

    public String getTitle() { return name; }

    public String getImage() { return image; }

    public int getId() { return id; }

    public void setTitle(String t) { name = t; }

    public void setImage(String b) { image = b; }

    public void rate(String u, int r) { ratings.put(u,r); }

    public int getRating(String u) { return ratings.get(u) == null ? 0 : ratings.get(u); }

    public Map<String,Integer> getRatings() { return ratings; }

    public int getAverageRating() {
        int avg = 0;
        for (int r : ratings.values()) {
            avg += r;
        }
        return ratings.size() > 0 ? avg / ratings.size() : 0;
    }

    public int getMajorRating() {
        int avg = 0, count = 0;
        UserManager um = new UserManager();
        for (Map.Entry<String,Integer> rating : ratings.entrySet()) {
            if (um.getUserMajor().equals(um.findUserMajor(rating.getKey()))) {
                avg += rating.getValue();
                count++;
            }
        }
        return count > 0 ? avg / count : 0;
    }

    public String toString() {
        return name + " " + id;
    }

    @Override
    public boolean equals(Object m) {
        return id == ((Movie)m).getId();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }
}