package br.com.consig.simulation.application.service;

import br.com.consig.simulation.adapter.in.rest.datatransfer.CustomerResponseDTO;
import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDTO;
import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDataDTO;
import br.com.consig.simulation.adapter.in.rest.mapper.CustomerMapper;
import br.com.consig.simulation.adapter.in.rest.mapper.SimulationMapper;
import br.com.consig.simulation.adapter.out.client.RecoverCustomerClient;
import br.com.consig.simulation.adapter.out.converter.CustomerConverter;
import br.com.consig.simulation.adapter.out.converter.SimulationConverter;
import br.com.consig.simulation.adapter.out.converter.SimulationDataConverter;
import br.com.consig.simulation.application.domain.entity.Customer;
import br.com.consig.simulation.application.domain.entity.Simulation;
import br.com.consig.simulation.application.domain.entity.SimulationData;
import br.com.consig.simulation.port.outbound.SimulationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class SimulationServiceTest {

    @Mock
    private SimulationRepository simulationRepository;
    @Mock
    private SimulationMapper simulationMapper;
    @Mock
    private CustomerConverter customerConverter;
    @Mock
    private SimulationDataConverter simulationDataConverter;
    @Mock
    private RecoverCustomerClient recoverCustomerClient;
    @InjectMocks
    private SimulationService simulationService;
    @Autowired
    private SimulationConverter simulationConverter;
    @Autowired
    private CustomerMapper customerMapper;

    Long id = 1L;
    String cpf = "111.111.111-11";
    LocalDateTime time = LocalDateTime.now();
    String covenant = "EP";
    Double requestedAmount = 1200.00;
    Double fee = 2.6;
    Integer numberInstallments = 12;
    Double paymentAmount = 1400.00;
    Double installmentValue = 123.50;
    String name = "Dummy";
    Boolean accountHolder = true;
    String segment = "Uniclass";

    @Test
    public void testGetAll() {

        List<Simulation> list = simulationRepository.findAll();

        assertNotNull(list, "The variable 'list' should be of type List");

    }

    @Test
    public void testFindByIdAndCpfWhenSimulationNotFound() {

        Long id = 1L;
        String cpf = "111.111.111-11";
        when(simulationRepository.findByIdAndCpf(id, cpf)).thenReturn(null);

        SimulationDTO result = simulationService.findByIdAndCpf(id, cpf);

        assertNull(result);
    }

    @Test
    public void testFindByIdAndCpfWhenSimulationFound() {

        Long id = 1L;
        String cpf = "111.111.111-11";
        LocalDateTime time = LocalDateTime.now();
        String covenant = "EP";
        Double requestedAmount = 1200.00;
        Double fee = 2.6;
        Integer numberInstallments = 12;
        Double paymentAmount = 1400.00;
        Double installmentValue = 123.50;
        Simulation simulation = new Simulation();
        simulation.setId(id);
        simulation.setCpf(cpf);

        when(simulationRepository.findByIdAndCpf(id, cpf)).thenReturn(simulation);

        SimulationDTO simulationDTO = new SimulationDTO(id, time, cpf, covenant, requestedAmount, fee,
                numberInstallments, paymentAmount, installmentValue);

        when(simulationMapper.toDTO(simulation)).thenReturn(simulationDTO);

        SimulationDTO result = simulationService.findByIdAndCpf(id, cpf);

        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals(cpf, result.cpf());
    }

    @Test
    public void testIsCorrentistaWhenCustomerIsAccountHolder() {

        Customer customer = new Customer();
        customer.setAccountHolder(true);

        Simulation simulation = new Simulation();
        simulation.setFee(100.0);

        Simulation result = simulationService.isCorrentista(customer, simulation);

        assertEquals(95.0, result.getFee(), 0.01);
    }

    @Test
    public void testIsCorrentistaWhenCustomerIsNotAccountHolder() {

        Customer customer = new Customer();
        customer.setAccountHolder(false);

        Simulation simulation = new Simulation();
        simulation.setFee(100.0);

        Simulation result = simulationService.isCorrentista(customer, simulation);

        assertEquals(100.0, result.getFee(), 0.01);
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    public void testNumberInstallmentsForSegment(int inputNumberInstallments, String segment, int expectedNumberInstallments) {
        SimulationData simulationData = new SimulationData();
        simulationData.setNumberInstallment(inputNumberInstallments);

        Customer customer = new Customer();
        customer.setSegment(segment);

        Simulation simulation = new Simulation();

        Simulation result = simulationService.numberInstallments(simulationData, customer, simulation);

        assertEquals(expectedNumberInstallments, result.getNumberInstallments());
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(30, "Varejo", 24),
                Arguments.of(40, "Uniclass", 36),
                Arguments.of(52, "Person", 48),
                Arguments.of(20, "N/A", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestData2")
    public void testSimulationFee(String covenant, double expectedFee) {

        Customer customer = new Customer();
        customer.setCovenant(covenant);

        Simulation simulation = new Simulation();

        Simulation result = simulationService.simulationFee(customer, simulation);

        assertEquals(expectedFee, result.getFee(), 0.01);
    }

    private static Stream<Arguments> provideTestData2() {
        return Stream.of(
                Arguments.of("EP", 2.6),
                Arguments.of("OP", 2.2),
                Arguments.of("INSS", 1.6)
                // Add more test cases as needed
        );
    }

    @Test
    public void testTotalAmount() {

        SimulationData simulationData = new SimulationData();
        simulationData.setRequestedAmount(100.0);

        Simulation simulation = new Simulation();
        simulation.setFee(2.0);
        simulation.setNumberInstallments(12);

        Simulation result = simulationService.totalAmount(simulationData, simulation);

        assertEquals(124.0, result.getPaymentAmount(), 0.01);
    }


    @Test
    public void testInstallmentValue() {

        Simulation simulation = new Simulation();
        simulation.setPaymentAmount(120.0);
        simulation.setNumberInstallments(6);

        Simulation result = simulationService.installmentValue(simulation);

        assertEquals(20.0, result.getInstallmentValue(), 0.01);
    }

}




