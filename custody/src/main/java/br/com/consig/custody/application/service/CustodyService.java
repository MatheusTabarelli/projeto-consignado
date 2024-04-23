package br.com.consig.custody.application.service;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyDTO;
import br.com.consig.custody.adapter.in.rest.mapper.CustodyMapper;
import br.com.consig.custody.adapter.out.client.SimulationClient;
import br.com.consig.custody.adapter.out.converter.CustodyConverter;
import br.com.consig.custody.application.domain.entity.Custody;
import br.com.consig.custody.port.inbound.CustodyUseCase;
import br.com.consig.custody.port.outbound.CustodyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustodyService implements CustodyUseCase {

    @Autowired
    private CustodyRepository custodyRepository;
    @Autowired
    private CustodyConverter custodyConverter;
    @Autowired
    private CustodyMapper custodyMapper;

    private final SimulationClient client;

    public Custody save(Custody custody) {
        return custodyRepository.save(custody);
    }

    @Override
    public List<CustodyDTO> findAll() {
        return custodyMapper.toDTO(custodyRepository.findAll());
    }

    @Override
    public CustodyDTO saveCustody(CustodyDTO custodyDTO) {
        Custody custody = save(custodyConverter.toEntity(custodyDTO));
        return custodyMapper.toDTO(custody);
    }



}
