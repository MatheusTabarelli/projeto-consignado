package br.com.consig.custody.adapter.in.rest.mapper;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyDTO;
import br.com.consig.custody.application.domain.entity.Custody;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustodyMapper {

    CustodyDTO toDTO(Custody custody);

    List<CustodyDTO> toDTO(List<Custody> custody);

}
