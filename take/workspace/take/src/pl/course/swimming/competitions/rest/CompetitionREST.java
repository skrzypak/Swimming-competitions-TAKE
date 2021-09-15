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

import pl.course.swimming.competitions.ejb.CompetitionEJB;
import pl.course.swimming.competitions.model.Competition;

/**
 * Competition REST API
 *
 * @version 1.0
 * @category REST
 */
@Path("/competition")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class CompetitionREST {

	/**
	 * The Bean.
	 */
	@EJB
	CompetitionEJB bean;

	/**
	 * Create new competition .
	 *
	 * @param competition POJO object
	 * 
	 * @return competition (200) - successfully created new competition
	 */
	@POST
	public Response create(Competition competition) {
		bean.create(competition);
		return Response.ok(competition).build();
	}

	/**
	 * Get all competitions.
	 *
	 * @return list of competitions (200) - successfully found competitions
	 * @return (404) - not found any competition
	 */
	@GET
	public Response get() {
		List<Competition> competitions = bean.get();
		
		if (competitions.size() > 0) {
			return Response.ok(competitions).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find by competition's id.
	 *
	 * @param idc the idc
	 * 
	 * @return competition (200) - successfully found competitions
	 * @return (404) - not found any competition
	 */
	@GET
	@Path("/{idc}")
	public Response findById(@PathParam("idc") long idc) {
		Competition competition = bean.find(idc);
		
		if (competition != null) {
			return Response.ok(competition).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find by competition's name.
	 *
	 * @param name the competition name
	 * 
	 * @return competition (200) - successfully found competitions
	 * @return (404) - not found any competition
	 */
	@GET
	@Path("/name")
	public Response findByName(@QueryParam("name") String name) {
		List<Competition> competitions = bean.findByName(name);
		
		if (competitions.size() > 0) {
			return Response.ok(competitions).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}


	/**
	 * Update competition.
	 *
	 * @param competition POJO object
	 * 
	 * @return (204) - successfully updated competition
	 * @return (400) - bad request
	 * @return (404) - not found any competition
	 */
	@PUT
	public Response update(Competition competition) {
		bean.update(competition);
		return Response.noContent().build();
	}


	/**
	 * Delete competition.
	 *
	 * @param idc the idc
	 * 
	 * @return (204) - successfully remove competition
	 * @return (404) - not found any competition
	 */
	@DELETE
	@Path("/{idc}")
	public Response delete(@PathParam("idc") long idc) {
		bean.delete(idc);
		return Response.noContent().build();
	}
}
