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
    public String image;
    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password, String Uri) {
        this.username = username;
        this.password = password;
        this.image = Uri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Movie> getMyList() {
        return myList;
    }

    public void setMyList(List<Movie> myList) {
        this.myList = myList;
    }

    public String getImageUri() {
        return image;
    }

    public void setImageUri(String imageUri) {
        this.image = imageUri;
    }
}
