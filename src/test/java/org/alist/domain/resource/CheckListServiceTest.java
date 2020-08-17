package org.alist.domain.resource;

import org.alist.domain.exceptions.NotFoundException;
import org.alist.domain.model.CheckList;
import org.alist.domain.repository.CheckListRepository;
import org.alist.domain.service.CheckListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckListServiceTest {

    @Mock
    CheckListRepository checkListRepository;

    @InjectMocks
    CheckListService checkListService;

    @Test
    void checkListCreation_shouldBeOk() {
        // ARRANGE
        final CheckList checkList = CheckList.builder()
                .id(1L)
                .name("chekList_name")
                .listItems(Arrays.asList("item_a", "item_b"))
                .build();
        when(checkListRepository.save(any(CheckList.class)))
                .thenReturn(checkList);

        // ACT
        assertThat(checkListService.create(checkList)).isEqualTo(checkList);
        verify(checkListRepository).save(checkList);
    }

    @Test
    void getAllCheckLists_shouldBeOk() {
        // ARRANGE
        final List<CheckList> checkLists = Arrays.asList(new CheckList(), new CheckList(), new CheckList());
        when(checkListRepository.all()).thenReturn(checkLists);

        //ACT
        assertThat(checkListService.findAll().size()).isEqualTo(3);
        verify(checkListRepository).all();
    }

    @Test
    void getOneCheckList_shouldBeOk() throws NotFoundException {
        // ARRANGE
        final CheckList checkList = CheckList.builder()
                .id(1L)
                .name("chekList_name")
                .listItems(Arrays.asList("item_a", "item_b"))
                .build();
        when(checkListRepository.findByCheckListId(1L)).thenReturn(Optional.of(checkList));

        // ACT
        assertThat(checkListService.getOne(1L)).isEqualTo(checkList);
        verify(checkListRepository).findByCheckListId(anyLong());
    }


}
