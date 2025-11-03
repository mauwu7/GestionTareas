package org.proyecto.pia_2.exception.handler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.proyecto.pia_2.exception.EquipoRegistradoException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.exception.UsuarioRegistradoException;
import org.proyecto.pia_2.exception.model.ErrorHandlerResponse;
import org.proyecto.pia_2.exception.model.ErrorResponse;
import org.springframework.boot.autoconfigure.batch.BatchTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class GlobalHandlerException {
    /*
    Estos metodos tienen que actualizarse con la nueva clase ErrorHandlerResponse

    @ExceptionHandler(UsuarioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUsuarioNotFoundException(UsuarioNotFoundException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ValidationErrorResponse handleConstraintViolationException(ConstraintViolationException ex){
        ValidationErrorResponse response = new ValidationErrorResponse();
        for(ConstraintViolation cv : ex.getConstraintViolations()){
            response.getViolations().add(new ConstrViolation(cv.getPropertyPath().toString(),cv.getMessage()));
        }
        return response;
    }
    @ExceptionHandler(EquipoRegistradoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEquipoRegistradoException(EquipoRegistradoException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
    }

     */

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlerResponse handleConstraintViolationException(ConstraintViolationException ex){

        return null;
    }

    @ExceptionHandler(UsuarioRegistradoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorHandlerResponse handleUsuarioRegistradoException(UsuarioRegistradoException ex) {
        List<ErrorResponse> errores = new ArrayList<>();
        errores.add(new ErrorResponse(null, ex.getMessage()));
        return new ErrorHandlerResponse(HttpStatus.CONFLICT.value(), errores);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlerResponse handleArgumentNotValidException(MethodArgumentNotValidException ex){

        List<FieldError> ErroresEnLosCampos = ex.getBindingResult().getFieldErrors();
        List<ErrorResponse> errores = new ArrayList<>();

        if(ErroresEnLosCampos == null){ //Por si acaso pero puede eliminarse
            errores.add(new ErrorResponse("Desconocido","Campos incorrectos"));
        }
        else{
            for(FieldError error : ErroresEnLosCampos) {
                errores.add(new ErrorResponse(error.getField(), error.getDefaultMessage()));
            }
        }

        return new ErrorHandlerResponse(HttpStatus.BAD_REQUEST.value(), errores );
    }

}
