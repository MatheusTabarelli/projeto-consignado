package br.com.consig.simulation.application.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column(name = "id")
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "simulation_date")
    private LocalDateTime simulationDate;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "covenant")
    private String covenant;

    @Column(name = "requested_amount")
    private Double requestedAmount;

    @Column(name = "fee")
    private Double fee;

    @Column(name = "number_installments")
    private Integer numberInstallments;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @Column(name = "installment_value")
    private Double installmentValue;

}
