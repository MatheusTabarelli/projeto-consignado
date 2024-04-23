package br.com.consig.customer.register.port.inbound;

import br.com.consig.customer.register.adapter.in.rest.datatransfer.CustomerResponseDTO;

import java.util.List;

public interface CustomerUsecase {

    CustomerResponseDTO findByCpf(String cpf);

    List<CustomerResponseDTO> findAll();

}
