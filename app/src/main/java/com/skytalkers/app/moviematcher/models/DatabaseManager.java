package com.skytalkers.app.moviematcher.models;


import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.List;

/**
 * Created by schuylerreinken on 2/23/16.
 */
public class DatabaseManager {
    static Firebase client = new Firebase("https://blazing-fire-2549.firebaseio.com/");
    static User currentUser;

    private class UserListener implements ValueEventListener {
        User data;
        String username;
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                User test = snapshot.getValue(User.class);
                if (((String) (test.getUsername())).equals(username)) {
                    DatabaseManager.currentUser = test;
                    data = test;
                    UserManager.setCheckedUser(test);
                    //throw new RuntimeException("This ran");
                }
            }
            data = new User();
            currentUser = new User();
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }

        public void setUser(String a) {
            username = a;
        }

        public User returnData() {
            return data;
        }
    }

    static void setUser(User a) {
        currentUser = a;
    }
    public User getData(String user) {
        currentUser = null;
        User p = null;
        UserListener listener = new UserListener();
        listener.setUser(user);
        client.child("Users").child("NormalUsers").addValueEventListener(listener);
        return p;

    }

    public void addUser(User user) {
        client.child("Users").child("NormalUsers").child(user.getUsername()).setValue(user);
    }

    public List<String> getAllUsers() {
        return null;
    }


}
