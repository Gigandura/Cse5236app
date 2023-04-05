package com.example.cse_5236_app.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@IgnoreExtraProperties
public class User {


    private String username;
    private String password;
    private String description;
//    private List<Movie> myList;
    private String image;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password, String Uri, String description) {
        this.username = username;
        this.password = password;
        this.image = Uri;
        this.description = description;
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

//    public List<Movie> getMyList() {
//        return myList;
//    }
//
//    public void setMyList(List<Movie> myList) {
//        this.myList = myList;
//    }

    public String getImage() {
        return this.image;
    }

    public void setImageUri(String imageUri) {
        this.image = imageUri;
    }

    public String getDescription() {
        return this.description;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
//                ", myList=" + myList +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
