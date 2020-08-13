package org.alist.infrastructure.persistence.postgresql.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.alist.domain.model.CheckList;
import org.alist.domain.repository.CheckListRepository;
import org.alist.infrastructure.persistence.postgresql.entity.CheckListEntity;
import org.alist.infrastructure.persistence.postgresql.mapper.CheckListMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CheckListPanacheRepository implements CheckListRepository, PanacheRepositoryBase<CheckListEntity, Long> {
    @Inject
    CheckListMapper checkListMapper;

    @Override
    public Optional<CheckList> findByCheckListId(Long checkListId) {
        return findByIdOptional(checkListId)
                .map(checkListMapper::toModel);
    }

    @Override
    public CheckList save(CheckList checkList) {
        final CheckListEntity entity = checkListMapper.toEntity(checkList);
        entity.persistAndFlush();
        return checkListMapper.toModel(entity);
    }

    @Override
    public List<CheckList> all() {
        return checkListMapper.toModels(listAll());
    }


}
