package dev.vardhman.resource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dev.vardhman.dto.UserDTO;
import dev.vardhman.model.User;
import dev.vardhman.service.UserService;
import io.smallrye.jwt.build.Jwt;
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

    // Create
    @POST
    @PermitAll
    public Response create(UserDTO user) {
        UserDTO createdUser = service.create(user);
        System.out.println("User created");
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @POST
    @Path("/login")
    public Response login(UserDTO user) {
        UserDTO savedUser = service.get(user);
        String jwt = generateJwt(savedUser);
        return Response.ok().header("Authorization", "Bearer " + jwt)
                .entity(savedUser).build();
    }

    public String generateJwt(UserDTO user) {
        Set<String> roles = new HashSet<>(
                Arrays.asList("admin", "user"));
        return Jwt.issuer("aspirant-hub")
                .upn(String.valueOf(user.getId()))
                .subject(String.valueOf(user.getEmail()))
                .groups(roles)
                .expiresAt(System.currentTimeMillis() + 2592000000l)
                .sign();
    }
}
