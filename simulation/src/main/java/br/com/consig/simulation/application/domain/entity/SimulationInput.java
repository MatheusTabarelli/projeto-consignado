package br.com.consig.simulation.application.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulationInput {

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("account_holder")
    private Boolean account_holder;

    @JsonProperty("segment")
    private String segment;

    @JsonProperty("covenant")
    private String covenant;

    @JsonProperty("requested_amount")
    private Double requested_amount;

}
