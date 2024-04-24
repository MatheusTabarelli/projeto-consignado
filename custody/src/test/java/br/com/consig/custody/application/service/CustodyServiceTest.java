package br.com.consig.custody.application.service;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyDTO;
import br.com.consig.custody.adapter.in.rest.mapper.CustodyInputMapper;
import br.com.consig.custody.application.domain.entity.Custody;
import br.com.consig.custody.application.domain.entity.CustodyInput;
import br.com.consig.custody.exception.InvalidRequestEception;
import br.com.consig.custody.port.outbound.CustodyRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustodyServiceTest {

    @Autowired
    private CustodyService custodyService;
    @Autowired
    private CustodyInputMapper custodyInputMapper;
    @Autowired
    private CustodyRepository custodyRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void testGetAll() {

        Custody custody = new Custody();
        custody.setSimulationId("1234");
        Custody custody2 = new Custody();
        custody2.setSimulationId("5678");

        custodyRepository.save(custody);
        custodyRepository.save(custody2);

        List<Custody> list = custodyRepository.findAll();

        Assertions.assertTrue(list instanceof List, "The variable 'list' should be of type List");

    }

    @Test
    public void testSave() {

        Custody custody = new Custody();
        custody.setSimulationId("1234");

        Custody custodySaved = custodyRepository.save(custody);

        Assertions.assertNotNull(custodySaved);
        Assertions.assertEquals( "1234", custodySaved.getSimulationId());

    }

    @Test
    void testSaveCustody_WithValidInput() {

        CustodyInput custodyInput = new CustodyInput();
        custodyInput.setSimulationId("1234");

        CustodyDTO usersEntity = custodyService.saveCustody(custodyInputMapper.toDTO(custodyInput));
        Assertions.assertEquals( "1234", usersEntity.simulationId());

    }

    @Test
    public void testSaveCustody_WithInvalidInput_ThrowsException() {
        CustodyInput custodyInput = new CustodyInput();
        custodyInput.setSimulationId("abcd");

        try {
            custodyService.saveCustody(custodyInputMapper.toDTO(custodyInput));
            fail("Expected InvalidRequestEception to be thrown");
        } catch (InvalidRequestEception e) {

        }

    }

    @Test
    public void testIsNumber_WithValidNumber_ReturnsTrue() {
        assertTrue(custodyService.isNumber("123"));
    }

    @Test
    public void testIsNumber_WithInvalidNumber_ReturnsFalse() {
        assertFalse(custodyService.isNumber("abc"));
    }

    @Test
    public void testIsNumber_WithEmptyNumber_ReturnsFalse() {
        assertFalse(custodyService.isNumber(""));
    }
}