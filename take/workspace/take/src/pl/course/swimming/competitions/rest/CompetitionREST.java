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
import javax.ws.rs.ext.Provider;

import pl.course.swimming.competitions.ejb.CompetitionEJB;
import pl.course.swimming.competitions.model.Competition;

/**
 * Competition API
 * @version 1.0
 * @category REST
 * */
@Provider
@Path("/competition")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class CompetitionREST {
	
	@EJB
	CompetitionEJB bean;

	@POST
	public Response create(Competition competition) {
		bean.create(competition);
		return Response.ok(competition).build();
	}
	
	@GET
	public Response get() {
		List<Competition> competitions = bean.get();
		
		if (competitions.size() > 0) {
			return Response.ok(competitions).build();
		}
		
		return Response.noContent().build();
	}

	@GET
	@Path("/{idc}")
	public Response findById(@PathParam("idc") long idc) {
		Competition competition = bean.find(idc);
		
		if (competition != null) {
			return Response.ok(competition).build();
		}
		
		return Response.noContent().build();
	}
	
	@GET
	@Path("/name")
	public Response findByName(@QueryParam("name") String name) {
		List<Competition> competitions = bean.findByName(name);
		
		if (competitions.size() > 0) {
			return Response.ok(competitions).build();
		}
		
		return Response.noContent().build();
	}
	

	@PUT
	public Response update(Competition competition) {
		bean.update(competition);
		return Response.ok().build();
	}


	@DELETE
	@Path("/{idc}")
	public Response delete(@PathParam("idc") long idc) {
		bean.delete(idc);
		return Response.ok().build();
	}
}
