package dev.vardhman.resource;

import dev.vardhman.auth.AuthenticationService;
import dev.vardhman.dto.UserDTO;
import dev.vardhman.model.User;
import dev.vardhman.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    UserService service;
    @Inject
    AuthenticationService authService;

    // Create
    @POST
    @PermitAll
    public Response create(User user) {
        UserDTO createdUser = service.create(user);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @POST
    @Path("/login")
    @PermitAll
    public Response login(User user) {

        UserDTO savedUser = service.get(user);
        String jwt = authService.generateJwt(savedUser);

        return Response.ok()
                .header("Authorization", "Bearer " + jwt)
                .entity(savedUser).build();
    }
}
