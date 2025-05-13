package dev.vardhman.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import dev.vardhman.dto.QuestionDTO;
import dev.vardhman.model.MultipleChoiceQuestion;
import dev.vardhman.model.Question;
import dev.vardhman.service.QuestionService;
import dev.vardhman.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/questions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QuestionResource {
    @Inject
    QuestionService questionService;

    @Inject
    JsonWebToken jwt;

    @POST
    @RolesAllowed({ "user" })
    public Response create(Question question) {
        Long userId = Long.parseLong(jwt.getName());
        QuestionDTO newQuestion = questionService.create(question, userId);
        return Response.status(Response.Status.CREATED).entity(newQuestion).build();
    }

    @GET
    @RolesAllowed({ "user" })
    public Response getAll() {
        Long userId = Long.parseLong(jwt.getName());
        List<Question> questions = questionService.getAll(userId);
        return Response.status(200).entity(questions).build();
    }
}
