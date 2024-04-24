package br.com.consig.customer.register.adapter.in.rest.controller;

import br.com.consig.customer.register.adapter.in.rest.datatransfer.CustomerResponseDTO;
import br.com.consig.customer.register.application.domain.entity.Customer;
import br.com.consig.customer.register.exception.InvalidRequestEception;
import br.com.consig.customer.register.exception.NotFoundException;
import br.com.consig.customer.register.port.inbound.CustomerUsecase;
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
public class CustomerRegisterControllerTest {

    @Autowired
    CustomerUsecase customerUsecase;
    @Autowired
    CustomerController customerController;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetAllCustody() {

        List<CustomerResponseDTO> expectedCustodies = customerUsecase.findAll();

        ResponseEntity<List<CustomerResponseDTO>> responseEntity = customerController.getAllCustomer();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedCustodies, responseEntity.getBody());
    }

    @Test
    public void testGetCustomerByCpf() {

        String cpf = "111.111.111-11";

        CustomerResponseDTO customer = customerUsecase.findByCpf(cpf);

        ResponseEntity<CustomerResponseDTO> responseEntity = customerController.getCustomerByCpf(cpf);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customer.cpf(), Objects.requireNonNull(responseEntity.getBody()).cpf());

    }

    @Test
    public void testGetCustomerByCpf_WithInvalidCpf_ThrowsException() {

        String cpf = "111.111.111-abc";

        try {
            customerController.getCustomerByCpf(cpf);
            fail("Expected InvalidRequestEception to be thrown");
        } catch (InvalidRequestEception e) {

        }
    }

    @Test
    public void testGetCustomerByCpf_WithNotExistingCpf_ThrowsException() {

        String cpf = "111.111.111-50";

        try {
            customerController.getCustomerByCpf(cpf);
            fail("Expected NotFoundException to be thrown");
        } catch (NotFoundException e) {

        }
    }
}
