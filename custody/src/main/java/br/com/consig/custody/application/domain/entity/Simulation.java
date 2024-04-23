package br.com.consig.custody.application.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_simulations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("simulation_date")
    private LocalDateTime simulation_date;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("covenant")
    private String covenant;

    @JsonProperty("requested_amount")
    private Double requested_amount;

    @JsonProperty("fee")
    private Double fee;

    @JsonProperty("number_installments")
    private Integer number_installments;

    @JsonProperty("payment_amount")
    private Double payment_amount;

    @JsonProperty("installment_value")
    private Double installment_value;

}
