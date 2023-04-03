package com.example.cse_5236_app.response;

import com.example.cse_5236_app.model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    @SerializedName("results")
    @Expose
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "SearchApi{" +
                "movie=" + movie +
                '}';
    }
}
