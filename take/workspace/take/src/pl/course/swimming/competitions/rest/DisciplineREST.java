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

import pl.course.swimming.competitions.ejb.DisciplineEJB;
import pl.course.swimming.competitions.model.Discipline;

/**
 * Discipline API
 * @version 1.0
 * @category REST
 * */
@Path("/discipline")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class DisciplineREST {
	
	@EJB
	DisciplineEJB bean;

	@POST
	public Response create(Discipline discipline) {
		bean.create(discipline);
		return Response.ok(discipline).build();
	}
	
	@GET
	public Response get() {
		List<Discipline> disciplines = bean.get();
		
		if (disciplines.size() > 0) {
			return Response.ok(disciplines).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/{idc}")
	public Response findById(@PathParam("idc") long idc) {
		Discipline discipline = bean.find(idc);
		
		if (discipline != null) {
			return Response.ok(discipline).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("/name")
	public Response findByName(@QueryParam("name") String name) {
		Discipline discipline = bean.findByName(name);
		
		if (discipline != null) {
			return Response.ok(discipline).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	

	@PUT
	public Response update(Discipline discipline) {
		bean.update(discipline);
		return Response.noContent().build();
	}


	@DELETE
	@Path("/{idc}")
	public Response delete(@PathParam("idc") long idc) {
		bean.delete(idc);
		return Response.noContent().build();
	}
}
