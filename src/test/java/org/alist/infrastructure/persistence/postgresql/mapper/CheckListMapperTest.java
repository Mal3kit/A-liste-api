package org.alist.infrastructure.persistence.postgresql.mapper;

import org.alist.domain.model.CheckList;
import org.alist.infrastructure.persistence.postgresql.entity.CheckListEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckListMapperTest {
    private static final Long CHECKLIST_ID = 1L;
    private static final String CHECKLIST_NAME = "checkListName";
    private static final List<String> CHECKLIST_ITEMS = Arrays.asList("item1", "item2", "item3");
    private static final Long CHECKLIST_ENTITY_ID = 2L;
    private static final String CHECKLIST_ENTITY_NAME = "checkListEntityName";
    private static final List<String> CHECKLIST_ENTITY_ITEMS = Arrays.asList("item4", "item5", "item6");
    private static final CheckList checkList = new CheckList()
            .builder()
            .id(CHECKLIST_ID)
            .name(CHECKLIST_NAME)
            .listItems(CHECKLIST_ITEMS)
            .build();
    private CheckListEntity checkListEntity;
    private CheckListMapper mapper;

    @BeforeAll
    public void setUp() {
        checkListEntity = new CheckListEntity();
        checkListEntity.id = CHECKLIST_ENTITY_ID;
        checkListEntity.name = CHECKLIST_ENTITY_NAME;
        checkListEntity.listItems = CHECKLIST_ENTITY_ITEMS;
        mapper = CheckListMapper.INSTANCE;
    }

    @Test
    void toModel_shouldBeOk() {
        CheckList checkList = new CheckList()
                .builder()
                .id(CHECKLIST_ENTITY_ID)
                .name(CHECKLIST_ENTITY_NAME)
                .listItems(CHECKLIST_ENTITY_ITEMS)
                .build();
        assertThat(mapper.toModel(checkListEntity)).isEqualTo(checkList);
    }

    @Test
    void toEntity_shouldBeOk() {
        CheckListEntity checkListEntity = new CheckListEntity();
        checkListEntity.id = CHECKLIST_ID;
        checkListEntity.name = CHECKLIST_NAME;
        checkListEntity.listItems = CHECKLIST_ITEMS;
        assertThat(mapper.toEntity(checkList)).isEqualTo(checkListEntity);
    }
}