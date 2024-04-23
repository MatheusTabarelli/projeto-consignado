package br.com.consig.simulation.adapter.in.rest.controller;

import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDTO;
import br.com.consig.simulation.adapter.in.rest.datatransfer.SimulationDataDTO;
import br.com.consig.simulation.port.inbound.SimulationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/simulation")
@RequiredArgsConstructor
public class SimulationController {

    @Autowired
    SimulationUseCase simulationUseCase;

    @GetMapping("/all")
    public ResponseEntity<List<SimulationDTO>> getAll() {
        return ResponseEntity.ok(simulationUseCase.findAll());
    }

    @GetMapping("/id/{id}/cpf/{cpf}")
    public ResponseEntity<SimulationDTO> getByIdAndCpf(@PathVariable Long id,@PathVariable String cpf) {
        return ResponseEntity.ok(simulationUseCase.findByIdAndCpf(id, cpf));
    }

    @PostMapping()
    public ResponseEntity<SimulationDTO> save(@RequestBody SimulationDataDTO simulationDataDTO) {
        return ResponseEntity.ok(simulationUseCase.saveSimulation(simulationDataDTO));
    }

}
