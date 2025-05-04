package dev.vardhman.resource;

import java.io.ObjectInputFilter.Status;

import dev.vardhman.dto.QuestionDTO;
import dev.vardhman.model.MultipleChoiceQuestion;
import dev.vardhman.model.Question;
import dev.vardhman.service.QuestionService;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/question")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QuestionResource {
    @Inject
    QuestionService service;
    @Inject
    SecurityIdentity securityIdentity;

    // create
    @POST
    public Response create(QuestionDTO question) {
        var me = securityIdentity.getPrincipal();
        System.out.println(me);
        Question newQuestion = service.create(question);
        return Response.status(Response.Status.CREATED).entity(newQuestion).build();
    }

    // get
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        var me = securityIdentity.getPrincipal();
        System.out.println(me);
        Question question = service.get(id);
        return Response.ok().entity(question).build();
    }

    // delete
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Question question = service.delete(id);
        return Response.ok().entity(question).build();
    }
}
