package org.alist.api.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.common.mapper.TypeRef;
import org.alist.api.mapper.CheckListMapper;
import org.alist.domain.model.CheckList;
import org.alist.domain.repository.CheckListRepository;
import org.alist.infrastructure.persistence.postgresql.config.PostgresOnlyTestEnvironment;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jupiter.MicroShedTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@MicroShedTest
@QuarkusTest
@SharedContainerConfig(PostgresOnlyTestEnvironment.class)
class CheckListResourceTest {

    @InjectMock
    CheckListRepository checkListRepository;

    private final CheckListMapper mapper = CheckListMapper.INSTANCE;

    private final CheckList CHECKLIST = new CheckList(1L, "checkListName", Collections.emptyList());


    @Test
    void findAllCheckLists() {
        // GIVEN
        List<CheckList> checkLists = Arrays.asList(new CheckList(), new CheckList(), new CheckList());

        // WHEN
        when(checkListRepository.all()).thenReturn(checkLists);
        List<CheckList> returnedChecklist = get("/checklist")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(getCheckListTypeRef());

        // THEN
        assertThat(returnedChecklist).isEqualTo(checkLists);
    }

    @Test
    void findById() {
        // WHEN
        when(checkListRepository.findByCheckListId(anyLong())).thenReturn(Optional.of(CHECKLIST));
        given()
                .pathParam("checkListId", 1L)
                .when()
                .get("/checklist/{checkListId}")
                .then()
                .statusCode(200)
                .header(CONTENT_TYPE, APPLICATION_JSON);
    }

    @Test
    void create() {
        given()
                .body(mapper.toDTO(CHECKLIST))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .post("/checklist")
                .then()
                .statusCode(CREATED.getStatusCode());
    }

    private TypeRef<List<CheckList>> getCheckListTypeRef() {
        return new TypeRef<List<CheckList>>() {
        };
    }
}