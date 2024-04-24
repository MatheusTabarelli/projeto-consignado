package br.com.consig.customer.register.adapter.in.rest.datatransfer;

public record  CustomerResponseDTO (

        Long id,

        String cpf,

        String name,

        Boolean accountHolder,

        String segment,

        String covenant){

}



