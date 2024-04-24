package br.com.consig.custody.port.inbound;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyDTO;
import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyInputDTO;

import java.util.List;

public interface CustodyUseCase {

    List<CustodyDTO> findAll();

    CustodyDTO saveCustody(CustodyInputDTO custodyInputDTO);

}
