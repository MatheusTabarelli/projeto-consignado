package br.com.consig.simulation.adapter.out.converter;

import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDTO;
import br.com.consig.simulation.application.domain.entity.Simulation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimulationConverter {

    public Simulation toEntity(SimulationDTO simulationDTO) {
        return Simulation.builder()
                .id(simulationDTO.id())
                .simulationDate(simulationDTO.simulationDate())
                .cpf(simulationDTO.cpf())
                .covenant(simulationDTO.covenant())
                .requestedAmount(simulationDTO.requestedAmount())
                .fee(simulationDTO.fee())
                .numberInstallments(simulationDTO.numberInstallments())
                .paymentAmount(simulationDTO.paymentAmount())
                .installmentValue(simulationDTO.installmentValue())
                .build();

    }

}
