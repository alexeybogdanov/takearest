package com.example.takearest.controller;

public class RestaurantNotFoundException extends RuntimeException {

    RestaurantNotFoundException(long id) {
        super("Could not find restaurant " + id);
    }
}
