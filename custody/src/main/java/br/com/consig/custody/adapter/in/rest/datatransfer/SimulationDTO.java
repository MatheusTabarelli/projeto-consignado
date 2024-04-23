package br.com.consig.custody.adapter.in.rest.datatransfer;

import java.time.LocalDateTime;

public record SimulationDTO(

        Long id,

        LocalDateTime simulation_date,

        String cpf,

        String covenant,

        Double requested_amount,

        Double fee,

        Integer number_installments,

        Double payment_amount,

        Double installment_value) {

}
