package org.example.backend.exception.handler;
import org.example.backend.exception.GrupoRegisteredException;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.exception.UserRegisteredException;
import org.example.backend.exception.model.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(UserRegisteredException.class)
    public ResponseEntity<ErrorDetails> UserRegisteredExceptionHandler(UserRegisteredException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), "email");
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> UserNotFoundExceptionHandler(UserNotFoundException ex ){
        return ResponseEntity.badRequest().body(new ErrorDetails(ex.getMessage(), "id"));
    }

    @ExceptionHandler(GrupoRegisteredException.class)
    public ResponseEntity<ErrorDetails> GrupoRegisteredExceptionHandler(GrupoRegisteredException ex){
        return ResponseEntity.badRequest().body(new ErrorDetails(ex.getMessage(), "nombreGrupo"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> BadCredentialsExceptionHandler(BadCredentialsException ex){
        return ResponseEntity.badRequest().body(new ErrorDetails(ex.getMessage(), "null"));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails> UserNameNotFoundExceptionHandler(UsernameNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorDetails(ex.getMessage(),"email"));
    }
}
