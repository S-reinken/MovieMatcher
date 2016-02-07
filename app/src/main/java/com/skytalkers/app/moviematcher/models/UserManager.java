package com.skytalkers.app.moviematcher.models;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by Bruce on 2/7/2016.
 * User manager to manage all registered users.
 */
public class UserManager {
    private static Map<String,User> users = new HashMap<>();

    public User findUser(String id) {return users.get(id);}

    public void addUser(String name, String pass) {
        users.put(name, new User(name, pass));
    }

    public Boolean login(String name, String pass) {
        User u = findUser(name);
        if (u == null) return false;
        return u.login(pass);
    }

}
