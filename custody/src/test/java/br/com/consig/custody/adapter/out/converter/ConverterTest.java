package br.com.consig.custody.adapter.out.converter;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyDTO;
import br.com.consig.custody.adapter.in.rest.mapper.CustodyMapper;
import br.com.consig.custody.application.domain.entity.Custody;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConverterTest {

    @Autowired
    CustodyMapper custodyMapper;

    @Test
    public void testCustodyToEntity() {

        CustodyConverter mapper = new CustodyConverter();
        Custody custody = new Custody();
        custody.setId(1L);
        custody.setContractDate(LocalDateTime.now());
        custody.setSimulationId("100");
        CustodyDTO custodyDTO = custodyMapper.toDTO(custody);

        Custody custodyConverted = mapper.toEntity(custodyDTO);

        assertNotNull(custodyConverted);
        assertEquals(custodyConverted.getId(), custody.getId());
        assertEquals(custodyConverted.getContractDate(), custody.getContractDate());
        assertEquals(custodyConverted.getSimulationId(), custody.getSimulationId());
    }

}
