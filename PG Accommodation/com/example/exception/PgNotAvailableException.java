package com.example.exception;

public class PgNotAvailableException extends RuntimeException {
    public PgNotAvailableException(String msg) {
        super(msg);
    }
}

