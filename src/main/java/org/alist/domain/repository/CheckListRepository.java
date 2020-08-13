package org.alist.domain.repository;

import org.alist.domain.model.CheckList;

import java.util.List;
import java.util.Optional;

public interface CheckListRepository {
    Optional<CheckList> findByCheckListId(Long checkListId);

    CheckList save(CheckList checkList);

    List<CheckList> all();
}
