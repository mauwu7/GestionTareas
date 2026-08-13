package org.example.backend.exception;

public class GrupoNotFoundException extends RuntimeException {
    public GrupoNotFoundException(String message) {
        super(message);
    }
}
