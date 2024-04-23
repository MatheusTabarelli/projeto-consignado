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

    @JsonProperty("requested_amount")
    private Double requested_amount;

    @JsonProperty("number_installment")
    private Integer number_installment;

}
