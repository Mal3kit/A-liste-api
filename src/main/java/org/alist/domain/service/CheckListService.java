package org.alist.domain.service;

import org.alist.domain.exceptions.NotFoundException;
import org.alist.domain.model.CheckList;
import org.alist.domain.repository.CheckListRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CheckListService {

    @Inject
    CheckListRepository checkListRepository;

    @Transactional
    public CheckList create(CheckList checkList) {
        return checkListRepository.save(checkList);
    }

    public List<CheckList> findAll() {
        return checkListRepository.all();
    }

    public CheckList getOne(Long id) throws NotFoundException {
        return checkListRepository.findByCheckListId(id)
                .orElseThrow(() -> new NotFoundException("CheckList not found for id " + id));
    }
}
