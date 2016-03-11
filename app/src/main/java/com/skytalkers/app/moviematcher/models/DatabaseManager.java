package com.skytalkers.app.moviematcher.models;


import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by schuylerreinken on 2/23/16.
 */
public class DatabaseManager {
    static Firebase client = new Firebase("https://blazing-fire-2549.firebaseio.com/");
    static List<User> userList = new ArrayList<User>();
    static List<User> adminList = new ArrayList<>();

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

    private class AdminListListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                adminList.add(snapshot.getValue(User.class));
            }
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    }

    public void addUser(User user) {
        client.child("Users").child("NormalUsers").child(user.getUsername()).setValue(user);
    }

    public void prepareUsers() {
        client.child("Users").child("NormalUsers").addValueEventListener(new ListListener());
        client.child("Users").child("Admins").addValueEventListener(new AdminListListener());
    }
    public List<User> getAllUsers() {
        return userList;
    }

    public List<User> getAdmins() {
        return  adminList;
    }


}
