package br.com.consig.custody.exception;

import java.util.List;

public class InvalidRequestEception extends ErrorException {

    private static final long serialVersionUID = 1837980503765010246L;

    public InvalidRequestEception(List<ErrorField> errorFields) {
        super("CONSIG_400", "Requisição inválida. Verifique os parâmetros e tente novamente", errorFields);
    }

    public InvalidRequestEception()
    {
        super("CONSIG_400", "Requisição inválida. Verifique os parâmetros e tente novamente");
    }}
