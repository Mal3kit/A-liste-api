package org.alist.api.resource;

import org.alist.api.dto.CheckListDTO;
import org.alist.api.mapper.CheckListMapper;
import org.alist.domain.exceptions.NotFoundException;
import org.alist.domain.model.CheckList;
import org.alist.domain.service.CheckListService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Path("/checklist")
@Produces(MediaType.APPLICATION_JSON)
public class CheckListResource {

    @Inject
    CheckListService checkListService;

    @Inject
    CheckListMapper checkListMapper;

    @GET
    @Operation(description = "Retrieve all checklists")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "All checklists successfully returned"),
            @APIResponse(responseCode = "204", description = "No checklists")
    })
    public Response findAllCheckLists() {
        final List<CheckList> allCheckLists = checkListService.findAll();
        if (Objects.isNull(allCheckLists) || allCheckLists.size() == 0) {
            return Response.noContent().build();
        } else {
            return Response.ok(checkListMapper.toDTOs(allCheckLists)).build();
        }
    }

    @GET
    @Path("/{checkListId}")
    @Operation(description = "Get a checklist using its id")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Checklist successfully returned"),
            @APIResponse(responseCode = "204", description = "Checklist not found for given id")
    })
    public Response findById(@PathParam("checkListId") Long checkListId) throws NotFoundException {
        final CheckList checkList = checkListService.getOne(checkListId);
        if (Objects.isNull(checkList)) {
            return Response.noContent().build();
        } else {
            return Response.ok(checkListMapper.toDTO(checkList)).build();
        }
    }

    @POST
    @Operation(description = "Create a new CheckList")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "CheckList successfully created")
    })
    public Response create(@Valid CheckListDTO checkListDTO) {
        checkListService.create(checkListMapper.toDomain(checkListDTO));
        return Response.status(Response.Status.CREATED).build();
    }
}
