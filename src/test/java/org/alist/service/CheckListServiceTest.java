package org.alist.service;

import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import org.alist.domain.CheckList;
import org.alist.repository.CheckListRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckListServiceTest {

    CheckListRepository checkListRepository;

    @Inject
    CheckListService checkListService;

    @BeforeAll
    public void setUp() {
        checkListRepository = mock(CheckListRepository.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkListCreation_shouldBeOk() {
        // ARRANGE
        final CheckList checkList = CheckList.builder()
                .id("chekList_id")
                .name("chekList_name")
                .listItems(Arrays.asList("item_a", "item_b"))
                .build();
        when(checkListRepository.create(any(CheckList.class)))
                .thenReturn(checkList);

        // ACT
        verify(checkListService.create(checkList)).equals(checkList);

    }
}