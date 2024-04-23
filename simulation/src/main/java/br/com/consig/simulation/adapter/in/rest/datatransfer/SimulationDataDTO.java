package br.com.consig.simulation.adapter.in.rest.datatransfer;

public record SimulationDataDTO (

        String cpf,

        Double requested_amount,

        Integer number_installment){

}
