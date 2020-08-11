package org.alist.service;

import org.alist.domain.CheckList;
import org.alist.repository.CheckListRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class CheckListService {

    @Inject
    CheckListRepository checkListRepository;

    @Transactional
    public CheckList create(CheckList checkList) {
        return checkListRepository.create(checkList);
    }
}
