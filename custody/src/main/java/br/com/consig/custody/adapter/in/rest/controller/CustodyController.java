package br.com.consig.custody.adapter.in.rest.controller;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyDTO;
import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyInputDTO;
import br.com.consig.custody.port.inbound.CustodyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/custody")
@RequiredArgsConstructor
public class CustodyController {

    @Autowired
    CustodyUseCase custodyUseCase;

    @GetMapping("/all")
    public ResponseEntity<List<CustodyDTO>> getAllCustody() {
        return ResponseEntity.ok(custodyUseCase.findAll());
    }

    @PostMapping()
    public ResponseEntity<CustodyDTO> save(@RequestBody CustodyInputDTO custodyInputDTO) {
        return ResponseEntity.ok(custodyUseCase.saveCustody(custodyInputDTO));
    }

}
