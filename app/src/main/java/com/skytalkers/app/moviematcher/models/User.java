package com.skytalkers.app.moviematcher.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruce on 2/3/2016.
 * Holds basic information about a user.
 */
public class User {
    private String username;
    private String password;
    private String first;
    private String last;
    private String email;
    private String major;
    private Map<String,Integer> ratings = new HashMap<>();
    private boolean banned = false;
    private boolean admin = false;

    public User() {

    }

    public User(String name, String password, String first, String last, String email, String major) {
        username = name;
        this.password = password;
        this.first = first;
        this.last = last;
        this.email = email;
        this.major = major;
    }

    public User(String name, String password, String first, String last, String email) {
        username = name;
        this.password = password;
        this.first = first;
        this.last = last;
        this.email = email;
        major = "";
    }

    public String getUsername() { return username; }

    public String getFirst() { return first; }

    public String getLast() { return last; }

    public String getMajor() { return major; }

    public String getPassword() { return password; }

    public Boolean login(String pass) { return pass.equals(password); }

    public void edit(String name, String f, String l, String e, String m) {
        username = name;
        first = f;
        last = l;
        email = e;
        major = m;
    }

    public void changePass(String pass) {
        password = pass;
    }

    public String getEmail() { return email; }

    public String toString() {
        return username + " " + first + " " + last + " " + email + " " + major;
    }

    public void rate(String m, int r) { ratings.put(m,r); }

    public int getRating(String m) { return ratings.get(m); }

    public boolean isAdmin() { return admin; }
    public boolean isBanned() { return banned; }
    public void setAdmin() { admin = true; }
    public void toggleBan(boolean ban) { banned = !ban; }
}
