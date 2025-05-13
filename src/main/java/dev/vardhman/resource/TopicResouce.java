package dev.vardhman.resource;

import java.util.List;

import javax.print.attribute.standard.Media;

import dev.vardhman.model.Topic;
import dev.vardhman.service.TopicService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/topics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TopicResouce {

    @Inject
    TopicService topicService;

    @POST
    @RolesAllowed({ "admin" })
    public Response create(Topic topic) {
        System.out.println(topic.getTopicName());
        Topic newTopic = topicService.create(topic);
        return Response.ok().entity(newTopic).build();
    }

    @GET
    @RolesAllowed({ "user" })
    public Response getAll() {
        List<Topic> topics = topicService.getAll();
        return Response.ok().entity(topics).build();
    }

}
