package org.proyecto.pia_2.exception.model;

public class ErrorResponse {
    private int statusCode;
    private String message;

    public ErrorResponse(String message, int statusCode) {
        this.statusCode = statusCode;
        this.message = message;

    }
}
