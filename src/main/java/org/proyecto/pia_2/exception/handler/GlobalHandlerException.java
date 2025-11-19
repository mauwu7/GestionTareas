package org.proyecto.pia_2.exception.handler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.proyecto.pia_2.exception.*;
import org.proyecto.pia_2.exception.model.ErrorHandlerResponse;
import org.proyecto.pia_2.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.ArrayList;
import java.util.List;


//Ver si se puede agrupar despues
@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlerResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        List<ErrorResponse> list = new ArrayList<>();
        list.add(new ErrorResponse(ex.getName(),ex.getMessage()));
        return new ErrorHandlerResponse(HttpStatus.BAD_REQUEST.value(),list);
    }

    @ExceptionHandler(TareaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorHandlerResponse handleTareaNotFoundException(TareaNotFoundException ex){
        List<ErrorResponse> errors = new ArrayList<>();
        errors.add(new ErrorResponse(null, ex.getMessage()));
        return new ErrorHandlerResponse(HttpStatus.NOT_FOUND.value(),errors);
    }

    @ExceptionHandler(EquipoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorHandlerResponse HandleEquipoNotFound(EquipoNotFoundException ex){
        List<ErrorResponse> listaErrores = new ArrayList<>();
        listaErrores.add(new ErrorResponse(null, "Equipo no encontrado"));
        return new ErrorHandlerResponse(HttpStatus.NOT_FOUND.value(), listaErrores);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorHandlerResponse usuarioNotFound(UsuarioNotFoundException ex){
        List<ErrorResponse> errores = new ArrayList<>();
        errores.add(new ErrorResponse(null,ex.getMessage()));
        return new ErrorHandlerResponse(HttpStatus.NOT_FOUND.value(), errores);
    }

    @ExceptionHandler(EquipoRegistradoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorHandlerResponse handleEquipoRegistradoException(EquipoRegistradoException ex) {
        List<ErrorResponse> errores = new ArrayList<>();
        errores.add(new ErrorResponse(null, ex.getMessage()));
        return new ErrorHandlerResponse(HttpStatus.CONFLICT.value(), errores);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlerResponse handleConstraintViolationException(ConstraintViolationException ex){
        ArrayList<ErrorResponse> errores = new ArrayList<>();
        for(ConstraintViolation<?> violation : ex.getConstraintViolations()){
            errores.add(new ErrorResponse(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return new ErrorHandlerResponse(HttpStatus.BAD_REQUEST.value(),errores);
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

    //Verificar despues que no pueda generar un problema
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlerResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        List<ErrorResponse> errores = new ArrayList<>();
        String mensaje = ex.getMessage();
        for(int index = 0; index < mensaje.length(); index++){
            if(mensaje.charAt(index) == ':'){
                mensaje = mensaje.substring(0,index);
            }
        }
        errores.add(new ErrorResponse(null, mensaje));
        return new ErrorHandlerResponse(HttpStatus.BAD_REQUEST.value(), errores);
    }

}
