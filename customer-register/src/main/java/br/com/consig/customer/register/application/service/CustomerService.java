package br.com.consig.customer.register.application.service;

import br.com.consig.customer.register.adapter.in.rest.datatransfer.CustomerResponseDTO;
import br.com.consig.customer.register.adapter.in.rest.mapper.CustomerMapper;
import br.com.consig.customer.register.application.domain.entity.Customer;
import br.com.consig.customer.register.port.inbound.CustomerUsecase;
import br.com.consig.customer.register.port.outbound.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//
//        if (!isValidFormat(cpf)) {
//            throw new IllegalArgumentException("O CPF informado está no formato inválido e deve ter o formato xxx.xxx.xxx-xx.");
//        }
        return customerMapper.toDTO(customer);

    }




//        if (!isValidFormat(cpf)) {
//            throw new IllegalArgumentException("O CPF informado está no formato inválido e deve ter o formato xxx.xxx.xxx-xx.");
//        }
//        if (customer == null) {
//            throw new NOContentException("O CPF informado não está cadastrado no banco de dados");
//        }
//        return customerMapper.toDTO(customer);


//    public static boolean isValidFormat(String cpf) {
//        String pattern = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
//        return cpf.matches(pattern);
//    }

}
