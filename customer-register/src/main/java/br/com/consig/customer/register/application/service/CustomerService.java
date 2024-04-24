package br.com.consig.customer.register.application.service;

import br.com.consig.customer.register.adapter.in.rest.datatransfer.CustomerResponseDTO;
import br.com.consig.customer.register.adapter.in.rest.mapper.CustomerMapper;
import br.com.consig.customer.register.application.domain.entity.Customer;
import br.com.consig.customer.register.exception.ErrorField;
import br.com.consig.customer.register.exception.InvalidRequestEception;
import br.com.consig.customer.register.exception.NotFoundException;
import br.com.consig.customer.register.port.inbound.CustomerUsecase;
import br.com.consig.customer.register.port.outbound.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUsecase {


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDTO> findAll() {
        return customerMapper.toDTO(customerRepository.findAll());
    }

    @Override
    public CustomerResponseDTO findByCpf(String cpf) {

        Customer customer = customerRepository.findByCpf(cpf);

        if (!isValidFormat(cpf)) {
            throw new InvalidRequestEception(Collections.singletonList(ErrorField.builder()
                    .field("cpf")
                    .message("O CPF informado está no formato inválido e deve ter o formato DDD.DDD.DDD-DD.")
                    .value(cpf)
                    .build()));
        }

        if (customer == null) {
            throw new NotFoundException(Collections.singletonList(ErrorField.builder()
                    .field("cpf")
                    .message("O CPF informado não está cadastrado no banco de dados.")
                    .value(cpf)
                    .build()));
        }

        return customerMapper.toDTO(customer);

    }

    public static boolean isValidFormat(String str) {
        String pattern = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        return str.matches(pattern);
    }

}
