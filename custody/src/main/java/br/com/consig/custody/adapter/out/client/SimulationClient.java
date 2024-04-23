package br.com.consig.custody.adapter.out.client;

import br.com.consig.custody.adapter.in.rest.datatransfer.SimulationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "localhost:8080", name = "simulation")
public interface SimulationClient {

    @GetMapping(value = "/simulation/id/{id}/cpf/{cpf}")
    SimulationDTO getSimulation(@PathVariable("id") Long id,@PathVariable("cpf") String cpf);

}
