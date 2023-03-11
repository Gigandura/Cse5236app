package com.example.cse_5236_app.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Movie {

    public String title;
    public Long rating;

    public Movie() {

    }

    public Movie(String title, Long rating) {
        this.title = title;
        this.rating = rating;
    }
}
