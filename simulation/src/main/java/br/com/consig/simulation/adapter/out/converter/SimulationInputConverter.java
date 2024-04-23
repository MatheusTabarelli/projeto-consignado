package br.com.consig.simulation.adapter.out.converter;

import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationInputDTO;
import br.com.consig.simulation.application.domain.entity.SimulationInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimulationInputConverter {

    public SimulationInput toEntity(SimulationInputDTO simulationInputDTO) {
        return SimulationInput.builder()
                .cpf(simulationInputDTO.cpf())
                .account_holder(simulationInputDTO.account_holder())
                .segment(simulationInputDTO.segment())
                .covenant(simulationInputDTO.covenant())
                .requested_amount(simulationInputDTO.requested_amount())
                .build();

    }

}
