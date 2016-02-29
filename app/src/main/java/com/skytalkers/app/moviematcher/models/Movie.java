package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;

public class Movie {
    private String name;
    private int id;
    private Bitmap image;

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

    public void setTitle(String t) {
        name = t;
    }

    public void setImage(Bitmap b) { image = b; }
}