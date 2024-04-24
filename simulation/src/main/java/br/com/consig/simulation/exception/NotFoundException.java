package br.com.consig.simulation.exception;

import java.util.List;

public class NotFoundException extends ErrorException {

    private static final long serialVersionUID = 7234073272924539509L;

    public NotFoundException(List<ErrorField> errorFields) {
        super("CONSIG_400", "Requisição inválida. Verifique os parâmetros e tente novamente", errorFields);
    }

}
