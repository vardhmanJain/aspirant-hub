package dev.vardhman.exception;

import dev.vardhman.model.ErrorMessage;
import jakarta.transaction.Status;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        ErrorMessage error = new ErrorMessage(
                "Internal Server Error. Please check if you are accessing the correct URL.", 404);
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }

}
