package org.alist.api.resource;

import org.alist.api.dto.CheckListDTO;
import org.alist.api.mapper.CheckListMapper;
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
            @APIResponse(responseCode = "200", description = "All checklists successfully returned")
    })
    public List<CheckListDTO> findAllCheckLists() {
        final List<CheckList> allCheckLists = checkListService.findAll();
        return checkListMapper.toDTOs(allCheckLists);
    }

    @GET
    @Path("/{checkListId}")
    @Operation(description = "Get a checklist using its id")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Checklist successfully returned"),
            @APIResponse(responseCode = "404", description = "Checklist not found for given id")
    })
    public CheckListDTO findById(@PathParam("checkListId") Long checkListId) {
        final CheckList checkList = checkListService.getOne(checkListId);
        return checkListMapper.toDTO(checkList);
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
