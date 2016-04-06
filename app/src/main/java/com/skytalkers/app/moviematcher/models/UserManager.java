package com.skytalkers.app.moviematcher.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Bruce on 2/7/2016.
 * User Manager object to manage all registered users.
 */
public class UserManager {
    /**
     * Map of usernames to users
     */
    private static Map<String,User> users;
    //private static DatabaseManager mgr = new DatabaseManager();
    /**
     * List of usernames
     */
    private static List<String> userList;
    //private static List<String> adminList = new ArrayList<>();
    /**
     * Current user being worked with
     */
    private static User user;

    /**
     * Constructs a UserManager object containing a list of usernames based on a map
     * from usernames to user objects, which will be created if it does not already exist.
     */
    public UserManager() {
        Log.d("UM constructor pre-init", "Test");
        if (users == null) {
            users = new HashMap<>();
            final List<User> uList = new DatabaseManager().getAllUsers();
            Log.d("UM constructor", String.valueOf(uList.size()));
            for (final User u : uList) {
                users.put(u.getUsername(), u);
            }
        }
        userList = new ArrayList<>(users.keySet());
    }

    /**
     * Sets the user currently being worked with.
     * @param name The username associated with the user
     */
    public void setUser(String name) { user = findUser(name); }

    /**
     * Finds the user object associated with a username
     * @param id The username of the user
     * @return The user object
     */
    public User findUser(String id) { return users.get(id); }
    /*public User findUser(String id) {
        adminList = mgr.getAdmins();
        userList = mgr.getAllUsers();
        for (User u : userList) {
            if (id.equals(u.getUsername())) {
                return u;
            }
        }
        return null;
    }*/

    /**
     * Changes whether a user is banned
     * @param name The username of the user
     */
    public void toggleBan(String name) {
        users.get(name).toggleBan(users.get(name).isBanned());
    }

    /**
     * Checks if the current user is an admin
     * @return True if the user is an admin, false otherwise
     */
    public boolean isAdmin() {
        return user.isAdmin();
    }

    /**
     * Checks whether a user is banned
     * @param name The username of the user
     * @return True if the user is banned, false otherwise
     */
    public boolean isBanned(String name) {
        return users.get(name).isBanned();
    }

    /*
    public void addAdmin(String name, String pass, String f, String l, String e, String m) {
        addUser(name, pass, f, l , e, m);
        users.get(name).setAdmin();
    }


    public void addAdmin(String name, String pass, String f, String l, String e) {
        addUser(name, pass, f, l, e);
        users.get(name).setAdmin();
    }
    */
    //public User getUser() { return user; }

    /*public void setUserList() {
        userList = mgr.getAllUsers();
    }*/

    /**
     * Fetches a username based on position in the user list
     * @param pos the index of the list to check
     * @return the username at the index
     */
    public String getUserByPos(int pos) { return userList.get(pos); }

    /**
     * Fetches the current user's username
     * @return the username
     */
    public String getUserName() { return user.getUsername(); }

    /**
     * Fetches the current user's first name
     * @return User's first name
     */
    public String getUserFirst() { return user.getFirst(); }

    /**
     * Fetches the current user's last name
     * @return User's last name
     */
    public String getUserLast() { return user.getLast(); }

    /**
     * Fetches the current user's email address
     * @return User's email address
     */
    public String getUserEmail() { return user.getEmail(); }

    /**
     * Fetches the current user's major
     * @return User's major
     */
    public String getUserMajor() { return user.getMajor(); }


    /**
     * Fetches a user's first name
     * @param name Username of the user
     * @return User's first name
     */
    public String findUserFirst(String name) { return users.get(name).getFirst(); }

    /**
     * Fetches a user's last name
     * @param name Username of the user
     * @return User's last name
     */
    public String findUserLast(String name) { return users.get(name).getLast(); }

    /**
     * Fetches a user's email address
     * @param name Username of the user
     * @return User's email address
     */
    public String findUserEmail(String name) { return users.get(name).getEmail(); }

    /**
     * Fetches a user's major
     * @param name Username of the user
     * @return User's major
     */
    public String findUserMajor(String name) { return users.get(name).getMajor(); }

    /**
     * Creates a new user, then adds it to the map of usernames to users, and the list of
     * usernames. Then creates a new instance of DatabaseManager and updates it with the new
     * set of users
     * @param name Username of the new user
     * @param pass Password of the new user
     * @param f First name of the new user
     * @param l Last name of the new user
     * @param e Email address of the new user
     * @param m Major of the new user
     */
    public void addUser(String name, String pass, String f, String l, String e, String m) {
        users.put(name, new User(name, pass, f, l, e, m));
        userList = new ArrayList<>(users.keySet());
        final DatabaseManager mgr = new DatabaseManager();
        mgr.addUser(users.get(name));
    }

    /*public void addUser(String name, String pass, String f, String l, String e, String m) {
        mgr.addUser(new User(name, pass, f, l, e, m));
    }*/



    /**
     * Creates a new user, then adds it to the map of usernames to users, and the list of
     * usernames
     * @param name Username of the new user
     * @param pass Password of the new user
     * @param f First name of the new user
     * @param l Last name of the new user
     * @param e Email address of the new user
     */
    public void addUser(String name, String pass, String f, String l, String e) {
        users.put(name, new User(name, pass, f, l, e, "NONE"));
        userList = new ArrayList<>(users.keySet());
        final DatabaseManager mgr = new DatabaseManager();
        mgr.addUser(users.get(name));
    }

    /*public void addUser(String name, String pass, String f, String l, String e) {
        mgr.addUser(new User(name, pass, f, l, e));
    }*/

    /**
     * Removes a user from the map of users
     * @param name Username of the user
     */
    public void deleteUser(String name) {
        users.remove(name);
        //userList = new ArrayList<>(users.keySet());
    }

    /**
     * Changes the information contained in the current user
     * @param name Updated username
     * @param f Updated first name
     * @param l Updated last name
     * @param e Updated email address
     * @param m Updated major
     */
    public void editUser(String name, String f, String l, String e, String m) {
        final DatabaseManager mgr = new DatabaseManager();
        users.remove(user.getUsername());
        user.edit(name, f, l, e, m);
        users.put(name, user);
        mgr.addUser(users.get(name));
        //userList = new ArrayList<>(users.keySet());
    }

    /**
     * Changes the password of the current user
     * @param pass Updated password
     */
    public void changePass(String pass) {
        final DatabaseManager mgr = new DatabaseManager();
        user.changePass(pass);
        users.put(user.getUsername(), user);
        mgr.addUser(user);
    }

    /**
     * Checks whether a password is correct for a user
     * @param name Username of the user
     * @param pass Password to check
     * @return True if password matches, false otherwise
     */
    public Boolean login(String name, String pass) {
        final User u = findUser(name);
        if (u == null) { return false; }
        return u.login(pass);
    }

    /**
     * Fetches the rating the current user gave a movie
     * @param m Name of the movie
     * @return Rating given by the user
     */
    public int getRating(String m) {
        return user.getRating(m);
    }

    /**
     * Gives a rating to a movie
     * @param m Name of the movie
     * @param r Rating to give the movie
     */
    public void rate(String m, int r) {
        user.rate(m,r);
    }

    /**
     * Logs the user out of the system
     */
    public void logout() {
        user = null;
    }

    /**
     * Fetches the list of usernames
     * @return List of usernames
     */
    public List<String> getUserList() {
        return userList;
    }
}
