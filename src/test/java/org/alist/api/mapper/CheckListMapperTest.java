package org.alist.api.mapper;

import org.alist.api.dto.CheckListDTO;
import org.alist.domain.model.CheckList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckListMapperTest {

    private static final Long CHECKLIST_ID = 1L;
    private static final String CHECKLIST_NAME = "checkListName";
    private static final List<String> CHECKLIST_ITEMS = Arrays.asList("item1", "item2", "item3");
    private static final Long CHECKLIST_DTO_ID = 2L;
    private static final String CHECKLIST_DTO_NAME = "checkListEntityName";
    private static final List<String> CHECKLIST_DTO_ITEMS = Arrays.asList("item4", "item5", "item6");
    private static final CheckList CHECKLIST = new CheckList()
            .builder()
            .id(CHECKLIST_ID)
            .name(CHECKLIST_NAME)
            .listItems(CHECKLIST_ITEMS)
            .build();
    private CheckListDTO checkListDTO;
    private CheckListMapper mapper;

    @BeforeAll
    public void setUp() {
        checkListDTO = new CheckListDTO();
        checkListDTO.id = CHECKLIST_DTO_ID;
        checkListDTO.name = CHECKLIST_DTO_NAME;
        checkListDTO.listItems = CHECKLIST_DTO_ITEMS;
        mapper = CheckListMapper.INSTANCE;
    }

    @Test
    void toDomain_shouldBeOk() {
        CheckList checkList = new CheckList()
                .builder()
                .id(CHECKLIST_DTO_ID)
                .name(CHECKLIST_DTO_NAME)
                .listItems(CHECKLIST_DTO_ITEMS)
                .build();
        assertThat(mapper.toDomain(checkListDTO)).isEqualTo(checkList);
    }

    @Test
    void toDto() {
        CheckListDTO checkListDTO = new CheckListDTO(CHECKLIST_ID,  CHECKLIST_NAME, CHECKLIST_ITEMS);
        assertThat(mapper.toDTO(CHECKLIST)).isEqualTo(checkListDTO);
    }
}