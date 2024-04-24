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
import br.com.consig.simulation.exception.ErrorField;
import br.com.consig.simulation.exception.InternalErrorException;
import br.com.consig.simulation.exception.InvalidRequestEception;
import br.com.consig.simulation.port.inbound.SimulationUseCase;
import br.com.consig.simulation.port.outbound.SimulationRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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


    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    DecimalFormat df = new DecimalFormat("#.##", symbols);

    @Override
    public List<SimulationDTO> findAll() {
        return simulationMapper.toDTO(simulationRepository.findAll());
    }

    @Override
    public SimulationDTO findByIdAndCpf(Long id, String cpf) {

        Simulation simulation = simulationRepository.findByIdAndCpf(id, cpf);

        return simulationMapper.toDTO(simulation);

    }

    public Simulation save(Simulation simulation) {
        return simulationRepository.save(simulation);
    }

    @Override
    public SimulationDTO saveSimulation(SimulationDataDTO simulationDataDTO) {

        if (simulationDataDTO.cpf() == null || simulationDataDTO.requestedAmount() == null
                || simulationDataDTO.numberInstallment() == null) {
            throw new InvalidRequestEception(Collections.singletonList(ErrorField.builder()
                    .field("cpf, requestedAmount, numberInstallment")
                    .message("Campo(s) não informado(s) ou fora do padrão: cpf, requestedAmount, numberInstallment. " +
                            "Verifique e tente novamente.")
                    .build()));
        }

        SimulationData simulationData = simulationDataConverter.toEntity(simulationDataDTO);
        CustomerResponseDTO customerDTO = getCustomer(simulationData.getCpf());
        Customer customer = customerConverter.toEntity(customerDTO);
        Simulation simulation = new Simulation();

        simulate(customer, simulation, simulationData);

        simulation.setSimulationDate(LocalDateTime.now());
        simulation.setCpf(customerDTO.cpf());
        simulation.setCovenant(customerDTO.covenant());
        simulation.setRequestedAmount(simulationDataDTO.requestedAmount());

        save(simulation);
        return simulationMapper.toDTO(simulation);

    }

    public CustomerResponseDTO getCustomer(String cpf) {
        try {
            return client.getCustomer(cpf);
        } catch (FeignException ex) {
            if (ex.status() == HttpStatus.BAD_REQUEST.value()) {
                throw new InvalidRequestEception(Collections.singletonList(ErrorField.builder()
                        .field("cpf")
                        .message("O CPF informado está fora do formato DDD.DDD.DDD-DD.")
                        .value(cpf)
                        .build()));
            }
            if (ex.status() == HttpStatus.NOT_FOUND.value()) {
                throw new InvalidRequestEception(Collections.singletonList(ErrorField.builder()
                        .field("cpf")
                        .message("O CPF informado não está cadastrado no banco de dados")
                        .value(cpf)
                        .build()));
            }

            throw new InternalErrorException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), "Erro de comunicação com cliente interno. Tente novamente mais tarde.");

        }
    }

    public Simulation simulate(Customer customer, Simulation simulation, SimulationData simulationData) {

        simulationFee(customer, simulation);
        isCorrentista(customer, simulation);
        numberInstallments(simulationData, customer, simulation);
        totalAmount(simulationData, simulation);
        installmentValue(simulation);

        return simulation;
    }

    public Simulation simulationFee(Customer customer, Simulation simulation) {
        String covenant = customer.getCovenant();

        if (covenant != null) {
            switch (covenant) {
                case "EP" -> simulation.setFee(2.6);
                case "OP" -> simulation.setFee(2.2);
                case "INSS" -> simulation.setFee(1.6);
                default -> {

                }
            }
        }

        return simulation;
    }

    public Simulation isCorrentista(Customer customer, Simulation simulation) {

        if (Boolean.TRUE.equals(customer.getAccountHolder())) {
            Double fee = (simulation.getFee() * 0.95);
            fee = Double.valueOf(df.format(fee));
            simulation.setFee(fee);
        }

        return simulation;

    }

    public Simulation numberInstallments(SimulationData simulationData, Customer customer, Simulation simulation) {

        String segment = customer.getSegment();

        if (Boolean.FALSE.equals(customer.getAccountHolder())) {
            segment = "N/A";
        }

        Integer number = simulationData.getNumberInstallment();

        switch (segment) {
            case "Varejo" -> {
                if (number > 24) {
                    simulation.setNumberInstallments(24);
                } else {
                    simulation.setNumberInstallments(number);
                }
            }
            case "Uniclass" -> {
                if (number > 36) {
                    simulation.setNumberInstallments(36);
                } else {
                    simulation.setNumberInstallments(number);
                }
            }
            case "Person" -> {
                if (number > 48) {
                    simulation.setNumberInstallments(48);
                } else {
                    simulation.setNumberInstallments(number);
                }
            }
            default -> {
                if (number > 12) {
                    simulation.setNumberInstallments(12);
                } else {
                    simulation.setNumberInstallments(number);
                }
            }

        }

        return simulation;

    }

    public Simulation totalAmount(SimulationData simulationData, Simulation simulation) {

        Double req = simulationData.getRequestedAmount();
        Integer numberInstallments = simulation.getNumberInstallments();
        Double simulationFee = simulation.getFee() / 100;
        Double fee = req * simulationFee * numberInstallments;
        Double total = req + fee;

        total = Double.valueOf(df.format(total));

        simulation.setPaymentAmount(total);

        return simulation;

    }

    public Simulation installmentValue(Simulation simulation) {

        Double installmentValue = simulation.getPaymentAmount() / simulation.getNumberInstallments();

        installmentValue = Double.valueOf(df.format(installmentValue));

        simulation.setInstallmentValue(installmentValue);

        return simulation;
    }

}
