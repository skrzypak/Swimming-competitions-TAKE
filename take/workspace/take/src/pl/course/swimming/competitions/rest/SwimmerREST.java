package pl.course.swimming.competitions.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import pl.course.swimming.competitions.ejb.SwimmerEJB;
import pl.course.swimming.competitions.model.Swimmer;

/**
 * Swimmer API
 * @version 1.0
 * @category REST
 * */
@Provider
@Path("/swimmer")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class SwimmerREST {
	
	@EJB
	SwimmerEJB bean;

	@POST
	public Response create(Swimmer swimmer) {
		bean.create(swimmer);
		return Response.ok(swimmer).build();
	}
	
	@GET
	public Response get() {
		
		List<Swimmer> swimmers = bean.get();
		
		if (swimmers.size() > 0) {
			return Response.ok(swimmers).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/{idc}")
	public Response findById(@PathParam("idc") long idc) {
		Swimmer swimmer = bean.find(idc);
		
		if (swimmer != null) {
			return Response.ok(swimmer).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("/fullname")
	public Response findByFullName(
			@QueryParam("name") String name,
			@QueryParam("surname") String surname) {
		List<Swimmer> swimmers = bean.findByFullName(name, surname);
		
		if (swimmers.size() > 0) {
			return Response.ok(swimmers).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	

	@PUT
	public Response update(Swimmer swimmer) {
		bean.update(swimmer);
		return Response.noContent().build();
	}


	@DELETE
	@Path("/{idc}")
	public Response delete(@PathParam("idc") long idc) {
		bean.delete(idc);
		return Response.noContent().build();
	}
}
