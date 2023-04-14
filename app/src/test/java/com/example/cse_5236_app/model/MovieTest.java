package com.example.cse_5236_app.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovieTest {

    private Movie movie = new Movie("title", "poster_link", "04/14/23", 1, (float) 4.078, "description", "english");

    @Test
    public void getTitle() {
        assertEquals("title", movie.getTitle());
    }

    @Test
    public void getPoster_path() {
        assertEquals("poster_link", movie.getPoster_path());
    }

    @Test
    public void getRelease_date() {
        assertEquals("04/14/23", movie.getRelease_date());
    }

    @Test
    public void getMovie_id() {
        assertEquals(1, movie.getMovie_id());
    }

    @Test
    public void getVote_average() {
        assertEquals((float) 4.078, movie.getVote_average(), 0.001);
    }

    @Test
    public void getMovie_overview() {
        assertEquals("description", movie.getMovie_overview());
    }

    @Test
    public void getOriginal_language() {
        assertEquals("english", movie.getOriginal_language());
    }
}