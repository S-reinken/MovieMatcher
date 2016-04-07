package com.skytalkers.app.moviematcher.models;


import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for communicating with Database
 */
public class DatabaseManager {
    //private static Firebase client = new Firebase("https://blazing-fire-2549.firebaseio.com/");
    /**
     * Firebase link
     */
    private static final Firebase CLIENT = new Firebase("https://resplendent-fire-3901.firebaseio.com/");
    /**
     * List of users
     */
    private static final List<User> USERLIST = new ArrayList<>();
    /**
     * List of movies
     */
    private static final List<Movie> MOVIELIST = new ArrayList<>();

    /**
     * Add a user to the database
     * @param user User object to add.
     */
    public void addUser(User user) {
        CLIENT.child("Users").child("NormalUsers").child(user.getUsername()).setValue(user);
        USERLIST.add(user);
    }

    /**
     * Add a movie to the database
     * @param movie Movie object to add
     */
    public void addMovie(Movie movie) {
        Log.d("DBM", movie.toString());
        final Firebase m = CLIENT.child("Movies").child(movie.getTitle());
        for (final String key : movie.getRatings().keySet()) {
            m.child("ratings").child(key).setValue(movie.getRating(key));
        }
        m.child("image").setValue(movie.getImage());
        m.child("id").setValue(movie.getId());
        MOVIELIST.add(movie);
    }

    /**
     * Add a rating to the database
     * @param m Movie to rate
     * @param u User rating it
     * @param r Rating value
     */
    public void rate(Movie m, String u, int r) {
        CLIENT.child("Movies").child(m.getTitle()).child("ratings").child(u).setValue(r);
    }

    /**
     * Database init stuff
     */
    public void prepareUsers() {
        CLIENT.child("Users").child("NormalUsers").addValueEventListener(new ListListener());
        CLIENT.child("Movies").addValueEventListener(new MovieListener());
        //CLIENT.child("Users").child("Admins").addValueEventListener(new AdminListListener());
    }

    /**
     * Pull User list
     * @return List of users
     */
    public List<User> getAllUsers() {
        return USERLIST;
    }

    /**
     * Pull Movie list
     * @return List of movies
     */
    public List<Movie> getMovieList() {
        return MOVIELIST;
    }


    private class ListListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                USERLIST.add(snapshot.getValue(User.class));
            }
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    }

    private class MovieListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (final DataSnapshot snp : dataSnapshot.getChildren()) {
                final Object id = snp.child("id").getValue();
                final Movie m = new Movie(snp.getKey(), id == null ? 0 :  ((Long) snp.child("id").getValue()).intValue(), (String) snp.child("image").getValue());
                for (final DataSnapshot sn : snp.child("ratings").getChildren()) {
                    m.rate(sn.getKey(), ((Long) sn.getValue()).intValue());
                }
                MOVIELIST.add(m);
            }
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    }
}
