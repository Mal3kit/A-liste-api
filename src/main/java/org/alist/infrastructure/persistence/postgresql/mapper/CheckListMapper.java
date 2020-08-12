package org.alist.infrastructure.persistence.postgresql.mapper;

import org.alist.domain.model.CheckList;
import org.alist.infrastructure.persistence.postgresql.entity.CheckListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface CheckListMapper {
    CheckListMapper INSTANCE = Mappers.getMapper(CheckListMapper.class);

    CheckListEntity toEntity(CheckList model);

    CheckList toModel(CheckListEntity entity);
}
