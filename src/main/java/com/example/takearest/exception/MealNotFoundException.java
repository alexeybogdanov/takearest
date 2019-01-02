package com.example.takearest.exception;

public class MealNotFoundException extends RuntimeException {

   public MealNotFoundException(long id) {
        super("Could not find meal " + id);
    }
}
