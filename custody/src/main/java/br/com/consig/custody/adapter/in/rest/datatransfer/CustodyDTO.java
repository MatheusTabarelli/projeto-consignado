package br.com.consig.custody.adapter.in.rest.datatransfer;

import java.time.LocalDateTime;

public record CustodyDTO (
        Long id,

        LocalDateTime contract_date,

        String simulation_id) {

}
