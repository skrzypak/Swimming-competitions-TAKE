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

import pl.course.swimming.competitions.ejb.SwimmerEJB;
import pl.course.swimming.competitions.model.Swimmer;

/**
 * Swimmer REST API
 *
 * @version 1.0
 * @category REST
 */
@Path("/swimmer")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class SwimmerREST {

	/**
	 * The Bean.
	 */
	@EJB
	SwimmerEJB bean;

	/**
	 * Create new swimmer .
	 *
	 * @param swimmer POJO object
	 * @return swimmer (200) - successfully created new swimmer
	 */
	@POST
	public Response create(Swimmer swimmer) {
		bean.create(swimmer);
		return Response.ok(swimmer).build();
	}

	/**
	 * Get all swimmers.
	 *
	 * @return list of swimmers (200) - successfully found swimmers
	 * @return (404) - not found any swimmer
	 */
	@GET
	public Response get() {
		
		List<Swimmer> swimmers = bean.get();
		
		if (swimmers.size() > 0) {
			return Response.ok(swimmers).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find by swimmer's id.
	 *
	 * @param idc the idc
	 * 
	 * @return swimmer (200) - successfully found swimmer
	 * @return (404) - not found any swimmer
	 */
	@GET
	@Path("/{idc}")
	public Response findById(@PathParam("idc") long idc) {
		Swimmer swimmer = bean.find(idc);
		
		if (swimmer != null) {
			return Response.ok(swimmer).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find swimmers by name
	 *
	 * @param name    the name
	 * @param surname the surname
	 * 
	 * @return list of swimmers (200) - successfully found swimmer
	 * @return (404) - not found any swimmer
	 */
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


	/**
	 * Update swimmer.
	 *
	 * @param swimmer the swimmer

	 * @return (204) - successfully updated swimmer
	 * @return (400) - bad request
	 * @return (404) - not found any swimmer
	 */
	@PUT
	public Response update(Swimmer swimmer) {
		bean.update(swimmer);
		return Response.noContent().build();
	}


	/**
	 * Delete swimmer.
	 *
	 * @param idc the idc
	 * 
	 * @return (204) - successfully remove swimmer
	 * @return (404) - not found any swimmer
	 */
	@DELETE
	@Path("/{idc}")
	public Response delete(@PathParam("idc") long idc) {
		bean.delete(idc);
		return Response.noContent().build();
	}
}
