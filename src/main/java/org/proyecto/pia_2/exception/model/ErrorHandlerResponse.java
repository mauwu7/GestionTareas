package org.proyecto.pia_2.exception.model;
import java.util.List;

public class ErrorHandlerResponse {
    private int status;
    private List<ErrorResponse> errores;

    public ErrorHandlerResponse(int status, List<ErrorResponse> errores) {
        this.status = status;
        this.errores = errores;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ErrorResponse> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorResponse> errores) {
        this.errores = errores;
    }
}
