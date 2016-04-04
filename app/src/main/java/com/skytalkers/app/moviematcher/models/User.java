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

    /**
     * Constructs a user object with no starting values
     */
    public User() {

    }

    /**
     * Constructs a user object
     * @param iname Username of the user
     * @param ipassword Password of the user
     * @param ifirst First name of the user
     * @param ilast Last name of the user
     * @param iemail Email address of the user
     * @param imajor Major of the user
     */
    public User(String iname, String ipassword, String ifirst, String ilast, String iemail, String imajor) {
        username = iname;
        this.password = ipassword;
        this.first = ifirst;
        this.last = ilast;
        this.email = iemail;
        this.major = imajor;
    }

    /**
     * Constructs a user object without a major parameter
     * @param iname Username of the user
     * @param ipassword Password of the user
     * @param ifirst First name of the user
     * @param ilast Last name of the user
     * @param iemail Email address of the user
     */
    public User(String iname, String ipassword, String ifirst, String ilast, String iemail) {
        username = iname;
        this.password = ipassword;
        this.first = ifirst;
        this.last = ilast;
        this.email = iemail;
        major = "";
    }

    /**
     * Fetches the user's username
     * @return The username
     */
    public String getUsername() { return username; }

    /**
     * Fetches the user's first name
     * @return The first name
     */
    public String getFirst() { return first; }

    /**
     * Fetches the user's last name
     * @return The last name
     */
    public String getLast() { return last; }

    /**
     * Fetches the user's major
     * @return The major
     */
    public String getMajor() { return major; }

    /**
     * Fetches the user's password
     * @return The password
     */
    public String getPassword() { return password; }

    /**
     * Checks if a provided password matches the user's password
     * @param pass Password to check
     * @return True if passwords match, otherwise false
     */
    public Boolean login(String pass) { return pass.equals(password); }

    /**
     * Changes the information stored in the user object
     * @param name Updated username
     * @param f Updated first name
     * @param l Updated last name
     * @param e Updated email address
     * @param m Updated major
     */
    public void edit(String name, String f, String l, String e, String m) {
        username = name;
        first = f;
        last = l;
        email = e;
        major = m;
    }

    /**
     * Changes the user's password
     * @param pass Updated password
     */
    public void changePass(String pass) {
        password = pass;
    }

    /**
     * Fetches the user's email address
     * @return The email address
     */
    public String getEmail() { return email; }

    /**
     * Gets a string containing the user's information
     * @return String with user info
     */
    public String toString() {
        return username + " " + first + " " + last + " " + email + " " + major;
    }

    /**
     * Adds a rating mapped to a movie
     * @param m Title of the movie to rate
     * @param r Rating of the movie
     */
    public void rate(String m, int r) { ratings.put(m,r); }

    /**
     * Checks the user's rating of a movie
     * @param m Title of the movie
     * @return Rating of the movie
     */
    public int getRating(String m) { return ratings.get(m); }

    /**
     * Checks whether the user is an admin
     * @return True if user is an admin, false otherwise
     */
    public boolean isAdmin() { return admin; }

    /**
     * Checks whether the user is banned
     * @return True if user is banned, false otherwise
     */
    public boolean isBanned() { return banned; }

    /**
     * Makes the user an admin
     */
    public void setAdmin() { admin = true; }

    /**
     * Changes whether the user is banned
     * @param ban Whether or not the user is currently banned
     */
    public void toggleBan(boolean ban) { banned = !ban; }
}
