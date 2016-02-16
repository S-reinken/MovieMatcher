package com.skytalkers.app.moviematcher.models;

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
    //boolean isAdmin = false;

    public User(String user, String pass, String f, String l, String e, String m) {
        username = user;
        password = pass;
        first = f;
        last = l;
        email = e;
        major = m;
    }

    public User(String user, String pass, String f, String l, String e) {
        username = user;
        password = pass;
        first = f;
        last = l;
        email = e;
        major = "";
    }

    public String getName() { return username; }

    public String getFirst() { return first; }

    public String getLast() { return last; }

    public String getMajor() { return major; }

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

    /*public boolean isAdmin() { return isAdmin; }*/
}
