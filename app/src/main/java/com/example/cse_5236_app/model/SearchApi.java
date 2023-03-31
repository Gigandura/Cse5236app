package com.example.cse_5236_app.model;

import java.util.List;

public class SearchApi {
    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    List<Movie> movieList = null;
}
