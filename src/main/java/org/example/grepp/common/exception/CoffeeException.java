package org.example.grepp.common.exception;

public abstract class CoffeeException extends RuntimeException {
    public CoffeeException(String message) {
        super(message);
    }
}
