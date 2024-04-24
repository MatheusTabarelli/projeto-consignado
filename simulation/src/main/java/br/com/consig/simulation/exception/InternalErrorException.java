package br.com.consig.simulation.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties({ "cause", "stackTrace", "localizedMessage", "message", "suppressed" })
public class InternalErrorException extends RuntimeException {

    private static final long serialVersionUID = 7234073272924539509L;

    private String errorCode;

    private String errorMessage;

    public InternalErrorException(int errorCode, String errorMessage) {
        this.errorCode = String.valueOf(errorCode);
        this.errorMessage = errorMessage;
    }

    public InternalErrorException(String errorMessage) {
        this.errorCode = "500";
        this.errorMessage = errorMessage;
    }

}
