package br.com.consig.simulation.application.service;

import br.com.consig.simulation.adapter.in.rest.datatransfer.CustomerResponseDTO;
import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDTO;
import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDataDTO;
import br.com.consig.simulation.adapter.in.rest.mapper.SimulationMapper;
import br.com.consig.simulation.adapter.out.client.RecoverCustomerClient;
import br.com.consig.simulation.adapter.out.converter.CustomerConverter;
import br.com.consig.simulation.adapter.out.converter.SimulationDataConverter;
import br.com.consig.simulation.application.domain.entity.Customer;
import br.com.consig.simulation.application.domain.entity.Simulation;
import br.com.consig.simulation.application.domain.entity.SimulationData;
import br.com.consig.simulation.port.inbound.SimulationUseCase;
import br.com.consig.simulation.port.outbound.SimulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulationService implements SimulationUseCase {

    @Autowired
    SimulationRepository simulationRepository;
    @Autowired
    SimulationMapper simulationMapper;
    @Autowired
    SimulationDataConverter simulationDataConverter;
    @Autowired
    CustomerConverter customerConverter;

    private final RecoverCustomerClient client;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public List<SimulationDTO> findAll() { return simulationMapper.toDTO(simulationRepository.findAll());
    }

    @Override
    public SimulationDTO findByIdAndCpf(Long id, String cpf) {

        Simulation simulation = simulationRepository.findByIdAndCpf(id, cpf);

        return  simulationMapper.toDTO(simulation);

    }

    public Simulation save(Simulation simulation) {
        return simulationRepository.save(simulation);
    }

    @Override
    public SimulationDTO saveSimulation(SimulationDataDTO simulationDataDTO) {

        SimulationData simulationData = simulationDataConverter.toEntity(simulationDataDTO);
        CustomerResponseDTO customerDTO = getCustomer(simulationData.getCpf());
        Customer customer = customerConverter.toEntity(customerDTO);
        Simulation simulation = new Simulation();

        simulate(customer, simulation, simulationData);

        simulation.setSimulation_date(LocalDateTime.now());
        simulation.setCpf(customerDTO.cpf());
        simulation.setCovenant(customerDTO.covenant());
        simulation.setRequested_amount(simulationDataDTO.requested_amount());

        save(simulation);
        return simulationMapper.toDTO(simulation);

    }

    public CustomerResponseDTO getCustomer(String cpf) {
        return client.getCustomer(cpf);
    }

    public Simulation simulate (Customer customer, Simulation simulation, SimulationData simulationData) {


        simulationFee(customer, simulation);
        isCorrentista(customer, simulation);
        numberInstallments(customer, simulation);
        totalAmount(simulationData, simulation);
        installmentValue(simulation);


        return simulation;
    }

    public Simulation simulationFee(Customer customer, Simulation simulation) {

        String covenant = customer.getCovenant();

        switch (covenant) {
            case "EP" -> simulation.setFee(2.6);
            case "OP" -> simulation.setFee(2.2);
            case "INSS" -> simulation.setFee(1.6);
        }

        return simulation;

    }

    public Simulation isCorrentista(Customer customer, Simulation simulation) {

        if (customer.getAccount_holder() == true) {
            Double fee = (simulation.getFee()*0.95);
            df.format(fee);
            simulation.setFee(fee);
        }

        return simulation;

    }

    public Simulation numberInstallments(Customer customer, Simulation simulation) {

        String segment = customer.getSegment();
        switch (segment) {
            case "Varejo" -> simulation.setNumber_installments(24);
            case "Uniclass" -> simulation.setNumber_installments(35);
            case "Person" -> simulation.setNumber_installments(48);
            default -> simulation.setNumber_installments(12);
        }

        return simulation;

    }

    public Simulation totalAmount(SimulationData simulationData, Simulation simulation) {

        Double req = simulationData.getRequested_amount();
        Double simulationFee = simulation.getFee()/100;
        Double fee = req * simulationFee;

        Double total = req + fee;
        df.format(total);

        simulation.setPayment_amount(total);

        return simulation;

    }

    public Simulation installmentValue(Simulation simulation) {

        Double installmentValue = simulation.getPayment_amount() / simulation.getNumber_installments();
        df.format(installmentValue);
        simulation.setInstallment_value(installmentValue);

        return simulation;
    }

}
