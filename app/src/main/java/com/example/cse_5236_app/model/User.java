package com.example.cse_5236_app.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String password;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
