package br.com.consig.customer.register.adapter.in.rest.mapper;

import br.com.consig.customer.register.adapter.in.rest.datatransfer.CustomerResponseDTO;
import br.com.consig.customer.register.application.domain.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDTO toDTO(Customer customer);

    List<CustomerResponseDTO> toDTO(List<Customer> customer);

}
