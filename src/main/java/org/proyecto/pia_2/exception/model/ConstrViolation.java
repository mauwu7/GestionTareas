package org.proyecto.pia_2.exception.model;

public class ConstrViolation {
    private String field;
    private String message;

    public ConstrViolation(String field, String message)
        {
        this.field = field;
        this.message = message;
        }

}
