package br.com.consig.simulation.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Error400 extends Error {

    public Error400() {super("400", "Requisição inválida. Verifique os parâmetros e tente novamente");}

    public Error400(String message) {super("400", message);}

    private List<ErrorField> errorFields = new ArrayList<>();

}
