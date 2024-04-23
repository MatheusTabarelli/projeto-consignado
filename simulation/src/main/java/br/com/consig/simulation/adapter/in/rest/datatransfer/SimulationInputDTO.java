package br.com.consig.simulation.adapter.in.rest.datatransfer;

public record SimulationInputDTO(

        String cpf,

        Boolean account_holder,

        String segment,

        String covenant,

        Double requested_amount){

}



