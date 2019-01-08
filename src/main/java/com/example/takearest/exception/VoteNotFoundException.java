package com.example.takearest.exception;

public class VoteNotFoundException extends RuntimeException {

    public VoteNotFoundException(String date) {
        super("Could not find vote for date " + date);
    }
}