package br.com.consig.custody.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorException extends RuntimeException {

    private static final long serialVersionUID = 6370906420308370792L;

    private final String errorCode;

    private final String errorDescription;

    private List<ErrorField> errorFields;

    public ErrorException(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public ErrorException(String errorCode, String errorDescription, List<ErrorField> errorFields) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorFields = errorFields;
    }
}
