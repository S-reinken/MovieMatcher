package com.skytalkers.app.moviematcher.models;

import java.util.HashMap;
import java.util.Map;

public class Movie {
    /**
     * Movie name
     */
    private String name;
    /**
     * Movie id
     */
    private int id;
    /**
     * Movie image string
     */
    private String image;
    /**
     * Movie ratings by user
     */
    private Map<String,Integer> ratings = new HashMap<>();

    //date released
    //ratinglist / array

    /**
     * Constructor to set name, id, and image
     * @param t Movie title
     * @param i Movie id
     * @param b Movie image
     */
    public Movie(String t, int i, String b) {
        name = t;
        id = i;
        image = b;
    }

    /**
     * Get movie title
     * @return Movie title
     */
    public String getTitle() { return name; }

    /**
     * Get movie image
     * @return Movie image
     */
    public String getImage() { return image; }

    /**
     * Get movie id
     * @return Movie id
     */
    public int getId() { return id; }

    /**
     * Set movie title
     * @param t Movie title
     */
    public void setTitle(String t) { name = t; }

    /**
     * Set movie image
     * @param b Movie image
     */
    public void setImage(String b) { image = b; }

    /**
     * Rate a movie
     * @param u User who is rating
     * @param r Rating value
     */
    public void rate(String u, int r) { ratings.put(u,r); }

    /**
     * Get rating value for a specific user
     * @param u User to get
     * @return rating value for user
     */
    public int getRating(String u) { return ratings.get(u) == null ? 0 : ratings.get(u); }

    /**
     * Get full set of User ratings
     * @return Map of User to ratings
     */
    public Map<String,Integer> getRatings() { return ratings; }

    /**
     * Get average rating of a movie
     * @return average rating value
     */
    public int getAverageRating() {
        int avg = 0;
        for (final int r : ratings.values()) {
            avg += r;
        }
        return ratings.size() > 0 ? avg / ratings.size() : 0;
    }

    /**
     * Get average rating for a specific major
     * @return rating for a major
     */
    public int getMajorRating() {
        int avg = 0, count = 0;
        final UserManager um = new UserManager();
        for (final Map.Entry<String,Integer> rating : ratings.entrySet()) {
            if (um.getUserMajor().equals(um.findUserMajor(rating.getKey()))) {
                avg += rating.getValue();
                count++;
            }
        }
        return count > 0 ? avg / count : 0;
    }

    @Override
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