package com.example.cse_5236_app.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Post {

    public String author;
    public String content;

    public Post() {

    }

    public Post(String author, String content) {
        this.author = author;
        this.content = content;
    }


}
