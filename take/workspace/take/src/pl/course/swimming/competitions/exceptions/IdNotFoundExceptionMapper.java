package pl.course.swimming.competitions.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapper for IdNotFoundException
 * @version 1.0
 * @category ExceptionMapper
 *  * @see pl.course.swimming.competitions.exceptions.IdNotFoundException
 * */
@Provider
public class IdNotFoundExceptionMapper implements ExceptionMapper<IdNotFoundException> {

	@Override
	public Response toResponse(IdNotFoundException arg0) {
		return Response.status(Status.NOT_FOUND).entity(arg0.getMessage()).build();
	}

}
