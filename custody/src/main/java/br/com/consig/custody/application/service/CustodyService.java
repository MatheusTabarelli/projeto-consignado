package br.com.consig.custody.application.service;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyDTO;
import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyInputDTO;
import br.com.consig.custody.adapter.in.rest.mapper.CustodyMapper;
import br.com.consig.custody.adapter.out.client.SimulationClient;
import br.com.consig.custody.adapter.out.converter.CustodyConverter;
import br.com.consig.custody.application.domain.entity.Custody;
import br.com.consig.custody.exception.ErrorField;
import br.com.consig.custody.exception.InvalidRequestEception;
import br.com.consig.custody.port.inbound.CustodyUseCase;
import br.com.consig.custody.port.outbound.CustodyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

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

    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Custody save(Custody custody) {
        return custodyRepository.save(custody);
    }

    @Override
    public List<CustodyDTO> findAll() {
        return custodyMapper.toDTO(custodyRepository.findAll());
    }

    @Override
    public CustodyDTO saveCustody(CustodyInputDTO custodyInputDTO) {

        Custody custody = new Custody();

        if (custodyInputDTO.simulationId() == null || !isNumber(custodyInputDTO.simulationId())) {
            throw new InvalidRequestEception(Collections.singletonList(ErrorField.builder()
                    .field("simulationId")
                    .message("O simulationId não foi informado ou está fora do padrão. Verifique e tente novamente.")
                    .build()));
        }

        custody.setContractDate(LocalDateTime.now());
        custody.setSimulationId(custodyInputDTO.simulationId());

        save(custody);
        return custodyMapper.toDTO(custody);

    }
    public static boolean isNumber(String str) {
        String numberPattern = "^[-+]?\\d*\\.?\\d+$";
        Pattern pattern = Pattern.compile(numberPattern);
        return pattern.matcher(str).matches();
    }


}
