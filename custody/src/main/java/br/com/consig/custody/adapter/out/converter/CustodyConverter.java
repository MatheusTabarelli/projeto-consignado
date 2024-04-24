package br.com.consig.custody.adapter.out.converter;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyDTO;
import br.com.consig.custody.application.domain.entity.Custody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustodyConverter {

    public Custody toEntity(CustodyDTO custodyDTO) {
        return Custody.builder()
                .id(custodyDTO.id())
                .contractDate(custodyDTO.contractDate())
                .simulationId(custodyDTO.simulationId())
                .build();

    }

}
