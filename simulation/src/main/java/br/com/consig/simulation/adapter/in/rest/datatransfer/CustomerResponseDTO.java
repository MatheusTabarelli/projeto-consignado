package br.com.consig.simulation.adapter.in.rest.datatransfer;

public record CustomerResponseDTO(

        Long id,

        String cpf,

        String name,

        Boolean accountHolder,

        String segment,

        String covenant){

}



