package org.alist.repository;

import org.alist.domain.CheckList;

public interface CheckListRepository {
    CheckList findByCheckListId(String checkListId);

    CheckList create(CheckList checkList);

}
