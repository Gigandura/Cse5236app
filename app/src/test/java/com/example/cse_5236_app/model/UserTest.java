package com.example.cse_5236_app.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

    private User user = new User("username", "password", "image_link", "description");

    @Test
    public void getUsername() {
        assertEquals("username", user.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void getImage() {
        assertEquals("image_link", user.getImage());
    }

    @Test
    public void getDescription() {
        assertEquals("description", user.getDescription());
    }

    @Test
    public void failure() {
        assertEquals("test", user.getDescription());
    }
}