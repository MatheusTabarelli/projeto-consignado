package br.com.consig.customer.register.application.service;

import br.com.consig.customer.register.adapter.in.rest.datatransfer.CustomerResponseDTO;
import br.com.consig.customer.register.application.domain.entity.Customer;
import br.com.consig.customer.register.exception.InvalidRequestEception;
import br.com.consig.customer.register.exception.NotFoundException;
import br.com.consig.customer.register.port.outbound.CustomerRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerRegisterServiceTest {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetAll() {

        List<Customer> list = customerRepository.findAll();

        assertNotNull(list, "The variable 'list' should be of type List");

    }

    @Test
    public void testGetByCpf() {

        String cpf = "111.111.111-11";

        Customer customer = customerRepository.findByCpf(cpf);

        assertNotNull(customer, "The variable customer should be of type Customer");
        assertEquals(customer.getCpf(), cpf);
    }

    @Test
    public void testGetByCpf_WithInvalidCpf_ThrowsException() {

        String cpf = "111.111.111-abc";

        try {
            customerService.findByCpf(cpf);
            fail("Expected InvalidRequestEception to be thrown");
        } catch (InvalidRequestEception e) {

        }
    }

    @Test
    public void testGetByCpf_WithNotExistingCpf_ThrowsException() {

        String cpf = "111.111.111-50";

        try {
            customerService.findByCpf(cpf);
            fail("Expected NotFoundException to be thrown");
        } catch (NotFoundException e) {

        }
    }

    @Test
    public void testValidFormat() {
        assertTrue(customerService.isValidFormat("123.456.789-01"));
        assertTrue(customerService.isValidFormat("999.999.999-99"));
    }

    @Test
    public void testInvalidFormat() {
        assertFalse(customerService.isValidFormat("12345678901"));
        assertFalse(customerService.isValidFormat("123.456.789-012"));
        assertFalse(customerService.isValidFormat("abc.def.ghi-jk"));
        assertFalse(customerService.isValidFormat("123.456-789"));
    }

    @Test
    public void testEmptyString() {
        assertFalse(customerService.isValidFormat(""));
    }
}


