package br.com.consig.customer.register.adapter.in.rest.datatransfer;

public record  CustomerResponseDTO (

        Long id,

        String cpf,

        String name,

        Boolean account_holder,

        String segment,

        String covenant){

}



