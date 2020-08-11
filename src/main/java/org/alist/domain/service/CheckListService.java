package org.alist.domain.service;

import org.alist.domain.model.CheckList;
import org.alist.domain.repository.CheckListRepository;

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
