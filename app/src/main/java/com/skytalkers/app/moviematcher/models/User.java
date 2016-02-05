package com.skytalkers.app.moviematcher.models;

/**
 * Created by Bruce on 2/3/2016.
 * Holds basic information about a user.
 */
public class User {
    private String nickname;
    private String username;
    private String password;
    private String email;
    boolean isAdmin = false;

    public User(String nick, String user, String pass, String eml) {
        nickname = nick;
        username = user;
        password = pass;
        email = eml;
    }

    public String getNick() {
        return nickname;
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}
