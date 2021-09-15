package pl.course.swimming.competitions.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapper for DuplicateException
 *
 * @version 1.0
 * @category ExceptionMapper
 * @see pl.course.swimming.competitions.exceptions.DuplicateException
 */
@Provider
public class DuplicateExceptionMapper implements ExceptionMapper<DuplicateException> {

	@Override
	public Response toResponse(DuplicateException arg0) {
		return Response.status(Status.CONFLICT).entity(arg0.getMessage()).build();
	}

}
