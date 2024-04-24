package br.com.consig.custody.adapter.in.rest.mapper;

import br.com.consig.custody.adapter.in.rest.datatransfer.CustodyInputDTO;
import br.com.consig.custody.application.domain.entity.CustodyInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustodyInputMapper {

    CustodyInputDTO toDTO(CustodyInput custody);

}
