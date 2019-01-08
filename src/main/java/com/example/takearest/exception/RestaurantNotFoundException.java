package com.example.takearest.exception;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(long id) {
        super("Could not find restaurant with id " + id);
    }

    public RestaurantNotFoundException(String name) {
        super("Could not find restaurant with name " + name);
    }
}
