package org.alist.infrastructure.persistence.postgresql.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.alist.domain.model.CheckList;
import org.alist.domain.repository.CheckListRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CheckListPanacheRepository implements CheckListRepository, PanacheRepositoryBase<CheckList, String> {
    @Override
    public CheckList findByCheckListId(String checkListId) {
        return null;
    }

    @Override
    public CheckList create(CheckList checkList) {
        return null;
    }
}
