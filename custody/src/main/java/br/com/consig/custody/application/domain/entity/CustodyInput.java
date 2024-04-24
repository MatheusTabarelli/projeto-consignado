package br.com.consig.custody.application.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustodyInput {

    @JsonProperty("simulationId")
    private String simulationId;

}
