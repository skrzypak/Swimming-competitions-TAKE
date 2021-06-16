package pl.course.swimming.competitions.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

	@Override
	public Response toResponse(IllegalArgumentException arg0) {
		return Response.status(Status.NOT_FOUND).entity(arg0.getMessage()).build();
	}

}