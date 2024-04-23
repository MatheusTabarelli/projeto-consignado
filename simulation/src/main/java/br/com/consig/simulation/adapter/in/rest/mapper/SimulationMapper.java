package br.com.consig.simulation.adapter.in.rest.mapper;

import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDTO;
import br.com.consig.simulation.application.domain.entity.Simulation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SimulationMapper {

    SimulationDTO toDTO(Simulation simulation);

    List<SimulationDTO> toDTO(List<Simulation> simulation);

}
