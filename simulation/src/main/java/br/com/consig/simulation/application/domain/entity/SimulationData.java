package br.com.consig.simulation.application.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulationData {

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("requestedAmount")
    private Double requestedAmount;

    @JsonProperty("numberInstallment")
    private Integer numberInstallment;

}
