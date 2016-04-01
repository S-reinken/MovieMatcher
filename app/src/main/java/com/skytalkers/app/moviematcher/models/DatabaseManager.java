package com.skytalkers.app.moviematcher.models;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schuylerreinken on 2/23/16.
 */
public class DatabaseManager {
    private static Firebase client = new Firebase("https://blazing-fire-2549.firebaseio.com/");
    private static List<User> userList = new ArrayList<User>();
    private static List<Movie> movieList = new ArrayList<>();

    public void addUser(User user) {
        client.child("Users").child("NormalUsers").child(user.getUsername()).setValue(user);
        userList.add(user);
    }

    public void addMovie(Movie movie) {
        for (String key : movie.getRatings().keySet()) {
            client.child("Movies").child(key).setValue(movie.getRating(key));
        }
        movieList.add(movie);
    }

    public void prepareUsers() {
        client.child("Users").child("NormalUsers").addValueEventListener(new ListListener());
        client.child("Movies").addValueEventListener(new MovieListener());
        //client.child("Users").child("Admins").addValueEventListener(new AdminListListener());
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    /*public List<User> getAdmins() {
        return  adminList;
    }*/


    private class ListListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                userList.add(snapshot.getValue(User.class));
            }
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    }

    private class MovieListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Movie m = new Movie(snapshot.getKey());
                for (DataSnapshot sn : snapshot.getChildren()) {
                    m.rate(sn.getKey(), ((Long)sn.getValue()).intValue());
                }
                movieList.add(m);
            }
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    }
}
