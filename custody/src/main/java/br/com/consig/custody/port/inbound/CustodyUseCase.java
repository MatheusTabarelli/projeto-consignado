package br.com.consig.custody.port.inbound;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyDTO;

import java.util.List;

public interface CustodyUseCase {

    List<CustodyDTO> findAll();

    CustodyDTO saveCustody(CustodyDTO custodyDTO);

}
