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

import pl.course.swimming.competitions.ejb.DisciplineEJB;
import pl.course.swimming.competitions.model.Discipline;

/**
 * Discipline REST API
 *
 * @version 1.0
 * @category REST
 */
@Path("/discipline")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class DisciplineREST {

	/**
	 * The Bean.
	 */
	@EJB
	DisciplineEJB bean;

	/**
	 * Create new discipline.
	 *
	 * @param discipline POJO object
	 * @return discipline (200) - successfully created new discipline
	 * @return (409) - discipline already exists
	 */
	@POST
	public Response create(Discipline discipline) {
		bean.create(discipline);
		return Response.ok(discipline).build();
	}

	/**
	 * Get all disciplines.
	 *
	 * @param discipline POJO object
	 * 
	 * @return list of disciplines (200) - successfully found disciplines
	 * @return (404) - not found any discipline
	 */
	@GET
	public Response get() {
		List<Discipline> disciplines = bean.get();
		
		if (disciplines.size() > 0) {
			return Response.ok(disciplines).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find discipline by id.
	 *
	 * @param idc the idc
	 * 
	 * @return discipline (200) - successfully found discipline
	 * @return (404) - not found any discipline
	 */
	@GET
	@Path("/{idc}")
	public Response findById(@PathParam("idc") long idc) {
		Discipline discipline = bean.find(idc);
		
		if (discipline != null) {
			return Response.ok(discipline).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find discipline by name.
	 *
	 * @param name the name
	 * 
	 * @return discipline (200) - successfully found discipline
	 * @return (404) - not found any discipline
	 */
	@GET
	@Path("/name")
	public Response findByName(@QueryParam("name") String name) {
		Discipline discipline = bean.findByName(name);
		
		if (discipline != null) {
			return Response.ok(discipline).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}


	/**
	 * Update discipline.
	 *
	 * @param discipline POJO object
	 * 
	 * @return (204) - successfully updated discipline
	 * @return (409) - discipline already exists
	 */
	@PUT
	public Response update(Discipline discipline) {
		bean.update(discipline);
		return Response.noContent().build();
	}


	/**
	 * Delete discipline.
	 *
	 * @param idc the idc
	 * 
	 * @return (204) - successfully remove discipline
	 * @return (404) - not found any discipline
	 */
	@DELETE
	@Path("/{idc}")
	public Response delete(@PathParam("idc") long idc) {
		bean.delete(idc);
		return Response.noContent().build();
	}
}
