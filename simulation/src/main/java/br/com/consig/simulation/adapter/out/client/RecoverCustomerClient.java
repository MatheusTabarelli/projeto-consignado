package br.com.consig.simulation.adapter.out.client;

import br.com.consig.simulation.adapter.in.rest.datatransfer.CustomerResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "localhost:8081", name = "customer-register")
public interface RecoverCustomerClient {

    @GetMapping(value = "/customers/{cpf}")
    CustomerResponseDTO getCustomer(@PathVariable("cpf") String cpf);
}
