package br.com.consig.custody.adapter.out.converter;

import br.com.consig.custody.adapter.in.rest.datatransfer.SimulationDTO;
import br.com.consig.custody.application.domain.entity.Simulation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimulationConverter {

    public Simulation toEntity(SimulationDTO simulationDTO) {
        return Simulation.builder()
                .id(simulationDTO.id())
                .simulation_date(simulationDTO.simulation_date())
                .cpf(simulationDTO.cpf())
                .covenant(simulationDTO.covenant())
                .requested_amount(simulationDTO.requested_amount())
                .fee(simulationDTO.fee())
                .number_installments(simulationDTO.number_installments())
                .payment_amount(simulationDTO.payment_amount())
                .installment_value(simulationDTO.installment_value())
                .build();

    }

}
