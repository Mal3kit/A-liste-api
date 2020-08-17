package org.alist.infrastructure.persistence.postgresql.repository;

import io.quarkus.test.junit.QuarkusTest;
import org.alist.domain.model.CheckList;
import org.alist.domain.repository.CheckListRepository;
import org.alist.infrastructure.persistence.postgresql.config.PostgresOnlyTestEnvironment;
import org.alist.infrastructure.persistence.postgresql.config.WithTransaction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jupiter.MicroShedTest;

import javax.inject.Inject;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@MicroShedTest
@QuarkusTest
@SharedContainerConfig(PostgresOnlyTestEnvironment.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CheckListPanacheRepositoryTest implements WithTransaction {

    @Inject
    CheckListRepository checkListRepository;

    @Test
    @Order(1)
    void save() throws Exception {
        final CheckList checkList = new CheckList(1L, "checkList", Arrays.asList("Item1", "Item2"));
        CheckList saveCheckList = withTransaction(() -> checkListRepository.save(checkList));
        assertThat(saveCheckList).isEqualTo(checkList);
    }

    @Test
    @Order(2)
    void findByCheckListId() throws Exception {
        final CheckList checkList = new CheckList(2L, "checkList2", Arrays.asList("Item1", "Item2"));
        withTransaction(() -> checkListRepository.save(checkList));
        assertThat(checkListRepository.findByCheckListId(2L).get()).isEqualTo(checkList);
    }

    @Test
    @Order(3)
    void all() {
        assertThat(checkListRepository.all().size()).isEqualTo(2);
    }
}