package br.com.consig.simulation.adapter.out.converter;

import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDataDTO;
import br.com.consig.simulation.application.domain.entity.SimulationData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimulationDataConverter {

    public SimulationData toEntity(SimulationDataDTO simulationDataDTO) {
        return SimulationData.builder()
                .cpf(simulationDataDTO.cpf())
                .requested_amount(simulationDataDTO.requested_amount())
                .number_installment(simulationDataDTO.number_installment())
                .build();
    }

}
