package org.alist.domain.repository;

import org.alist.domain.model.CheckList;

public interface CheckListRepository {
    CheckList findByCheckListId(String checkListId);

    CheckList create(CheckList checkList);

}
