package org.proyecto.pia_2.exception.handler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.exception.UsuarioRegistradoException;
import org.proyecto.pia_2.exception.model.ConstrViolation;
import org.proyecto.pia_2.exception.model.ErrorResponse;
import org.proyecto.pia_2.exception.model.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(UsuarioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUsuarioNotFoundException(UsuarioNotFoundException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(UsuarioRegistradoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUsuarioRegistradoException(UsuarioRegistradoException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
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





}
