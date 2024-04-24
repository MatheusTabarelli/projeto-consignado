package br.com.consig.custody.exception.handlers;

import br.com.consig.custody.exception.Error400;
import br.com.consig.custody.exception.Error404;
import br.com.consig.custody.exception.InvalidRequestEception;
import br.com.consig.custody.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestEception.class)
    public ResponseEntity<Error400> exceptionInvalidRequest(InvalidRequestEception ex) {
        Error400 error = new Error400(ex.getErrorDescription());
        error.setErrorFields(ex.getErrorFields());
        return new ResponseEntity<>(
                error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error404> exceptionNotFound(NotFoundException ex) {
        Error404 error = new Error404(ex.getErrorDescription());
        error.setErrorFields(ex.getErrorFields());
        return new ResponseEntity<>(
                error, HttpStatus.NOT_FOUND);
    }


}
