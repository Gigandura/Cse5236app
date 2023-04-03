package com.example.cse_5236_app.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class User {

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Movie> getMyList() {
        return myList;
    }

    public String username;
    public String password;

    public List<Movie> myList;
    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
