package org.alist.api.mapper;

import org.alist.api.dto.CheckListDTO;
import org.alist.domain.model.CheckList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CheckListMapper {
    CheckListMapper INSTANCE = Mappers.getMapper(CheckListMapper.class);

    CheckList toDomain(CheckListDTO dto);

    CheckListDTO toDTO(CheckList model);

    List<CheckListDTO> toDTOs(List<CheckList> models);
}
