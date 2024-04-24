package br.com.consig.customer.register.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error {

    private final String code;
    private final String message;

}
