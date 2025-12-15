package com.example.exception;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String msg) {
        super(msg);
    }
}

