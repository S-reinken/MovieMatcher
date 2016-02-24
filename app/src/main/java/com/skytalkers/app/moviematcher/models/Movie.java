package com.skytalkers.app.moviematcher.models;

public class Movie {
    private String name;
    private int id;

    //date released
    //ratinglist / array

    public Movie() {

    }

    public Movie(String t) {
        name= t;
    }

    public Movie(String t, int i) {
        name = t;
        id = i;
    }

    public String getTitle() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String t) {
        name = t;
    }
}