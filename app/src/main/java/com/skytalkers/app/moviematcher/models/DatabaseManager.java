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
    //private static Firebase client = new Firebase("https://blazing-fire-2549.firebaseio.com/");
    private static Firebase client = new Firebase("https://resplendent-fire-3901.firebaseio.com/");
    private static List<User> userList = new ArrayList<>();
    private static List<Movie> movieList = new ArrayList<>();

    public void addUser(User user) {
        client.child("Users").child("NormalUsers").child(user.getUsername()).setValue(user);
        userList.add(user);
    }

    public void addMovie(Movie movie) {
        final Firebase m = client.child("Movies").child(movie.getTitle());
        for (final String key : movie.getRatings().keySet()) {
            m.child("ratings").child(key).setValue(movie.getRating(key));
        }
        m.child("image").setValue(movie.getImage());
        m.child("id").setValue(movie.getId());
        movieList.add(movie);
    }

    public void rate(Movie m, String u, int r) {
        client.child("Movies").child(m.getTitle()).child("ratings").child(u).setValue(r);
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

    private class ListListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
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
            for (final DataSnapshot snp : dataSnapshot.getChildren()) {
                //Log.d("DatabaseManager", snp.toString());
                final Movie m = new Movie(snp.getKey(), ((Long)snp.child("id").getValue()).intValue(), (String)snp.child("image").getValue());
                for (final DataSnapshot sn : snp.child("ratings").getChildren()) {
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
