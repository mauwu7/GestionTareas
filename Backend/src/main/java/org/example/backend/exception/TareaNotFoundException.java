package org.example.backend.exception;

public class TareaNotFoundException extends RuntimeException {
    public TareaNotFoundException(String message) {
        super(message);
    }
}
