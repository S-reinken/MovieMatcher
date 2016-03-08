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
    static List<User> list = new ArrayList<User>();

    private class ListListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                list.add(snapshot.getValue(User.class));
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
    }
    public List<User> getAllUsers() {
        return list;
    }


}
