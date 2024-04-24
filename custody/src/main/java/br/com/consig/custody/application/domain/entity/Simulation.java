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
    private LocalDateTime simulationDate;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("covenant")
    private String covenant;

    @JsonProperty("requestedAmount")
    private Double requestedAmount;

    @JsonProperty("fee")
    private Double fee;

    @JsonProperty("numberInstallments")
    private Integer numberInstallments;

    @JsonProperty("payment_amount")
    private Double paymentAmount;

    @JsonProperty("installment_value")
    private Double installmentValue;

}
