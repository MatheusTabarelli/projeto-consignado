package br.com.consig.simulation.application.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("name")
    private String name;

    @JsonProperty("account_holder")
    private Boolean accountHolder;

    @JsonProperty("segment")
    private String segment;

    @JsonProperty("covenant")
    private String covenant;

}
