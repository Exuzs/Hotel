package com.example.Hotel.exception;

public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(String message) {
        super(message);
    }
}

