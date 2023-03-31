package com.example.cse_5236_app.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Movie {

    public String title = "";

    public int year = 0;

    public String image = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int rank) {
        this.year = rank;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Movie() {

    }

}
