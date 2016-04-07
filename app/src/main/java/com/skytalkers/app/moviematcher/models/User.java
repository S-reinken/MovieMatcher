package com.skytalkers.app.moviematcher.models;


/**
 * Created by Bruce on 2/3/2016.
 * Holds basic information about a user.
 */
public class User {
    /**
     * User name
     */
    private String username;
    /**
     * User password
     */
    private String password;
    /**
     * User first name
     */
    private String first;
    /**
     * User last name
     */
    private String last;
    /**
     * User  email
     */
    private String email;
    /**
     * User major
     */
    private String major;
    /**
     * User ratings
     */
    //private Map<String,Integer> ratings = new HashMap<>();
    /**
     * User ban status
     */
    private boolean banned = false;
    /**
     * User admin status
     */
    private boolean admin = false;

    /**
     * Constructs a user object with no starting values
     */
    public User() {

    }

    /**
     * Constructs a user object
     * @param name Username of the user
     * @param p Password of the user
     * @param f First name of the user
     * @param l Last name of the user
     * @param e Email address of the user
     * @param m Major of the user
     * @param a User admin status
     */
    public User(String name, String p, String f, String l, String e, String m, boolean a) {
        username = name;
        this.password = p;
        this.first = f;
        this.last = l;
        this.email = e;
        this.major = m;
        this.admin = a;
    }

    /**
     * Constructs a user object without a major parameter
     * @param name Username of the user
     * @param p Password of the user
     * @param f First name of the user
     * @param l Last name of the user
     * @param e Email address of the user
     */
    public User(String name, String p, String f, String l, String e) {
        username = name;
        this.password = p;
        this.first = f;
        this.last = l;
        this.email = e;
        major = "NONE";
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
    //public void rate(String m, int r) { ratings.put(m,r); }

    /**
     * Checks the user's rating of a movie
     * @param m Title of the movie
     * @return Rating of the movie
     */
    //public int getRating(String m) { return ratings.get(m); }

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
    //public void setAdmin() { admin = true; }

    /**
     * Changes whether the user is banned
     * @param ban Whether or not the user is currently banned
     */
    public void toggleBan(boolean ban) { banned = !ban; }
}
