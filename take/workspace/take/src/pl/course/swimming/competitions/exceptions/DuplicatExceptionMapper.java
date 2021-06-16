package pl.course.swimming.competitions.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DuplicatExceptionMapper implements ExceptionMapper<DuplicatException> {

	@Override
	public Response toResponse(DuplicatException arg0) {
		return Response.status(Status.CONFLICT).entity(arg0.getMessage()).build();
	}

}
