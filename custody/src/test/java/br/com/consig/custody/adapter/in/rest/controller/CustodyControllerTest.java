package br.com.consig.custody.adapter.in.rest.controller;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyDTO;
import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyInputDTO;
import br.com.consig.custody.adapter.in.rest.mapper.CustodyInputMapper;
import br.com.consig.custody.adapter.in.rest.mapper.CustodyMapper;
import br.com.consig.custody.application.domain.entity.CustodyInput;
import br.com.consig.custody.exception.InvalidRequestEception;
import br.com.consig.custody.port.inbound.CustodyUseCase;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustodyControllerTest {

    @Autowired
    private CustodyUseCase custodyUseCase;
    @Autowired
    private CustodyController custodyController;
    @Autowired
    private CustodyMapper custodyMapper;
    @Autowired
    private CustodyInputMapper custodyInputMapper;
    @Rule

    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetAllCustody() {

        List<CustodyDTO> expectedCustodies = custodyUseCase.findAll();

        ResponseEntity<List<CustodyDTO>> responseEntity = custodyController.getAllCustody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedCustodies, responseEntity.getBody());
    }

    @Test
    public void testSaveCustody() {

        CustodyInput custodyInput = new CustodyInput();
        custodyInput.setSimulationId("1234");
        CustodyInputDTO custodyInputDTO = custodyInputMapper.toDTO(custodyInput);

        CustodyDTO saveCustody = custodyUseCase.saveCustody(custodyInputDTO);

        ResponseEntity<CustodyDTO> responseEntity = custodyController.save(custodyInputDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(saveCustody.simulationId(), Objects.requireNonNull(responseEntity.getBody()).simulationId());

    }

    @Test
    public void testSaveCustody_WithInvalidInput_ThrowsException() {
        CustodyInput custodyInput = new CustodyInput();
        custodyInput.setSimulationId("abcd");

        try {
            custodyController.save(custodyInputMapper.toDTO(custodyInput));
            fail("Expected InvalidRequestEception to be thrown");
        } catch (InvalidRequestEception e) {

        }

    }

}
