package org.proyecto.pia_2.exception.model;

public class ErrorResponse {
    private String field;
    private String errorMessage;
    public ErrorResponse(String field, String errorMessage){
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
