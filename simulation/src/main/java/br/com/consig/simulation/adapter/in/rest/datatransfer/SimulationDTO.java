package br.com.consig.simulation.adapter.in.rest.datatransfer;

import java.time.LocalDateTime;

public record SimulationDTO (

        Long id,

        LocalDateTime simulationDate,

        String cpf,

        String covenant,

        Double requestedAmount,

        Double fee,

        Integer numberInstallments,

        Double paymentAmount,

        Double installmentValue) {

}
