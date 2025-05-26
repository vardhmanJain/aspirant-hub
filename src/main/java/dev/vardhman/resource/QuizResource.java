package dev.vardhman.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import dev.vardhman.service.QuizService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.POST;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/quiz")
public class QuizResource {
    // Inject the QuizService
    @Inject
    QuizService quizService;
    @Inject
    JsonWebToken jwt;

    // Define the endpoint to create a quiz
    @POST
    @RolesAllowed({ "user" })
    public Response createQuiz(@QueryParam("topics") List<Long> topicids, @QueryParam("count") int count) {
        for (Long topicId : topicids) {
            System.out.println("Topic ID: " + topicId);
        }
        System.out.println("Count: " + count);
        System.out.println("get user id from jwt");
        Long userId = Long.parseLong(jwt.getName());
        // Call the service to create the quiz
        System.out.println("Creating quiz for user ID: " + userId);
        var quiz = quizService.create(userId, topicids, count);

        // Return the created quiz in the response
        return Response.status(Response.Status.CREATED).entity(quiz).build();
    }
}
