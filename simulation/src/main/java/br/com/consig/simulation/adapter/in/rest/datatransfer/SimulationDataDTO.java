package br.com.consig.simulation.adapter.in.rest.datatransfer;

public record SimulationDataDTO (

        String cpf,

        Double requestedAmount,

        Integer numberInstallment){

}
