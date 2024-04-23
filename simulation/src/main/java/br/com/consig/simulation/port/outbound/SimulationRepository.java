package br.com.consig.simulation.port.outbound;

import br.com.consig.simulation.application.domain.entity.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimulationRepository extends JpaRepository<Simulation, Long> {

    List<Simulation> findAll();

    Simulation findByIdAndCpf(Long id, String cpf);

    Simulation save(Simulation simulation);

}
