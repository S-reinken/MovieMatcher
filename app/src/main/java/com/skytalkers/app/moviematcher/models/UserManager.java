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
    private static Map<String,User> users;
    private static DatabaseManager mgr = new DatabaseManager();
    private static List<String> userList;
    private static List<String> adminList = new ArrayList<>();
    private static User user;

    public UserManager() {
        Log.d("UM constructor pre-init", "Test");
        if (users == null) {
            users = new HashMap<>();
            List<User> uList = new DatabaseManager().getAllUsers();
            Log.d("UM constructor", String.valueOf(uList.size()));
            for (User u : uList) users.put(u.getUsername(), u);
        }
        userList = new ArrayList<>(users.keySet());
    }

    public void setUser(String name) { user = findUser(name); }

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

    public void toggleBan(String name) {
        users.get(name).toggleBan(users.get(name).isBanned());
    }

    public boolean isAdmin() {
        return user.isAdmin();
    }

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
    public String getUserByPos(int pos) { return userList.get(pos); }

    public String getUserName() { return user.getUsername(); }

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
        DatabaseManager mgr = new DatabaseManager();
        mgr.addUser(users.get(name));
    }

    /*public void addUser(String name, String pass, String f, String l, String e, String m) {
        mgr.addUser(new User(name, pass, f, l, e, m));
    }*/


    public void addUser(String name, String pass, String f, String l, String e) {
        users.put(name, new User(name, pass, f, l, e));
        userList = new ArrayList<>(users.keySet());
    }

    /*public void addUser(String name, String pass, String f, String l, String e) {
        mgr.addUser(new User(name, pass, f, l, e));
    }*/

    public void deleteUser(String name) {
        users.remove(name);
        //userList = new ArrayList<>(users.keySet());
    }

    public void editUser(String name, String f, String l, String e, String m) {
        users.remove(user.getUsername());
        user.edit(name, f, l, e, m);
        users.put(name, user);
        //userList = new ArrayList<>(users.keySet());
    }

    public void changePass(String pass) {
        user.changePass(pass);
        users.put(user.getUsername(), user);
    }

    public Boolean login(String name, String pass) {
        User u = findUser(name);
        System.out.println("Not in the activity yet");
        if (u == null) return false;
        return u.login(pass);
    }

    public int getRating(String m) {
        return user.getRating(m);
    }

    public void rate(String m, int r) {
        user.rate(m,r);
    }

    public void logout() {
        user = null;
    }

    public List<String> getUserList() {
        return userList;
    }
}
