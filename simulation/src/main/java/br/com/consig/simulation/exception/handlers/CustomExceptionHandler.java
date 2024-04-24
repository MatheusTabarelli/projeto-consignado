package br.com.consig.simulation.exception.handlers;

import br.com.consig.simulation.exception.*;
import br.com.consig.simulation.exception.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

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

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<Object> handleFeignException(FeignException ex, WebRequest request) throws IOException {
        if ((ex.status() == 400 || ex.status() == 404) && ex.getMessage().contains("content")) {
            String[] content = ex.getMessage().split("content:");
            String errorBody = content[content.length - 1];
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<>(mapper.readTree(errorBody), new HttpHeaders(),
                    HttpStatus.valueOf(ex.status()));
        } else {
            return new ResponseEntity<>(new Error(Integer.toString(ex.status()), ex.getMessage()),
                    HttpStatus.valueOf(ex.status()));
        }
    }

    @ExceptionHandler(InternalErrorException.class)
    public final ResponseEntity<Error> handleInternalError(InternalErrorException ex) {
        return new ResponseEntity<>(new Error(ex.getErrorCode(), ex.getErrorMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
