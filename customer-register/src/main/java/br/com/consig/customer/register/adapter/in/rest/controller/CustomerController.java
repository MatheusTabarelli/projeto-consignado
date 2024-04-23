package br.com.consig.customer.register.adapter.in.rest.controller;

import br.com.consig.customer.register.adapter.in.rest.datatransfer.CustomerResponseDTO;
import br.com.consig.customer.register.port.inbound.CustomerUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerUsecase customerUsecase;

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomer() {
        return ResponseEntity.ok(customerUsecase.findAll());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(customerUsecase.findByCpf(cpf));
    }

}
