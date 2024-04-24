package br.com.consig.simulation.adapter.out.converter;

import br.com.consig.simulation.adapter.in.rest.datatransfer.CustomerResponseDTO;
import br.com.consig.simulation.application.domain.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerConverter {

    public Customer toEntity(CustomerResponseDTO customerResponseDTO) {
        return Customer.builder()
                .cpf(customerResponseDTO.cpf())
                .name(customerResponseDTO.name())
                .accountHolder(customerResponseDTO.accountHolder())
                .segment(customerResponseDTO.segment())
                .covenant(customerResponseDTO.covenant())
                .build();
    }
}
