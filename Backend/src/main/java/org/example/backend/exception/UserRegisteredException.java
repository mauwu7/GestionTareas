package org.example.backend.exception;

public class UserRegisteredException extends RuntimeException {
    public UserRegisteredException(String message) {
        super(message);
    }
}
