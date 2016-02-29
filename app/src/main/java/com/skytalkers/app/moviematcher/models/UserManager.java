package com.skytalkers.app.moviematcher.models;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Bruce on 2/7/2016.
 * User Manager object to manage all registered users.
 */
public class UserManager {
    private static Map<String,User> users = new HashMap<>();
    private static ArrayList<String> userList;
    private static User user;


    public void setUser(String name) { user = findUser(name); }

    public User findUser(String id) { return users.get(id); }

    //public User getUser() { return user; }

    public String getUserByPos(int pos) { return userList.get(pos); }

    public String getUserName() { return user.getName(); }

    public String getUserFirst() { return user.getFirst(); }

    public String getUserLast() { return user.getLast(); }

    public String getUserEmail() { return user.getEmail(); }

    public String getUserMajor() { return user.getMajor(); }

    public String findUserFirst(String name) { return users.get(name).getFirst(); }
    public String findUserLast(String name) { return users.get(name).getLast(); }
    public String findUserEmail(String name) { return users.get(name).getEmail(); }
    public String findUserMajor(String name) { return users.get(name).getMajor(); }

    public void addUser(String name, String pass, String f, String l, String e, String m) {
        users.put(name, new User(name, pass, f, l, e, m));
        userList = new ArrayList<>(users.keySet());
    }

    public void addUser(String name, String pass, String f, String l, String e) {
        users.put(name, new User(name, pass, f, l, e));
        userList = new ArrayList<>(users.keySet());
    }

    public void deleteUser(String name) {
        users.remove(name);
        userList = new ArrayList<>(users.keySet());
    }

    public void editUser(String name, String f, String l, String e, String m) {
        users.remove(user.getName());
        user.edit(name, f, l, e, m);
        users.put(name, user);
        userList = new ArrayList<>(users.keySet());
    }

    public void changePass(String pass) {
        user.changePass(pass);
        users.put(user.getName(), user);
    }

    public Boolean login(String name, String pass) {
        User u = findUser(name);
        if (u == null) return false;
        return u.login(pass);
    }

    public void logout() {
        user = null;
    }

    public ArrayList<String> getUserList() {
        return userList;
    }

}
