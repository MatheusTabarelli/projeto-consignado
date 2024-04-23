package br.com.consig.simulation.port.inbound;

import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDTO;
import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDataDTO;

import java.util.List;

public interface SimulationUseCase {

    List<SimulationDTO> findAll();

    SimulationDTO findByIdAndCpf(Long id, String cpf);

    SimulationDTO saveSimulation(SimulationDataDTO simulationDataDTO);

}
