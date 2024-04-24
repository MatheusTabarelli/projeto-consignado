package br.com.consig.simulation.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Error404 extends Error {

    public Error404(String message) { super("404", message);}

    private List<ErrorField> errorFields = new ArrayList<>();

}
