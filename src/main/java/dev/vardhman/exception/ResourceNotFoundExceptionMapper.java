package dev.vardhman.exception;

import dev.vardhman.model.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

    @Override
    public Response toResponse(ResourceNotFoundException exception) {
        ErrorMessage error = new ErrorMessage("Requested resource not found.", 404);
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }

}
