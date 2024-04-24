package br.com.consig.custody.adapter.out.converter;

import br.com.consig.custody.application.domain.entity.CustodyInput;

public class CustodyInputConverter {

    public CustodyInput toEntity(br.com.consig.custody.adapter.in.rest.datatransfer.CustodyInputDTO custodyInputDTO) {
        return CustodyInput.builder()
                .simulationId(custodyInputDTO.simulationId())
                .build();

    }
}
