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

import pl.course.swimming.competitions.dto.ResultDto;
import pl.course.swimming.competitions.ejb.ResultEJB;
import pl.course.swimming.competitions.model.Result;

/**
 * Result REST API
 *
 * @version 1.0
 * @category REST
 */
@Path("/result")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ResultREST {

	/**
	 * The Bean.
	 */
	@EJB
	ResultEJB bean;

	/**
	 * Create new result.
	 *
	 * @param resultDto DTO of result
	 * 
	 * @return result (200) - successfully created new result
	 */
	@POST
	public Response create(ResultDto resultDto) {
		Result resp = bean.create(resultDto);
		return Response.ok(resp).build();
	}


	/**
	 * Get all results.
	 *
	 * @return list of results (200) - successfully found results
	 * @return (404) - not found any result
	 */
	@GET
	@Path("/")
	public Response get() {
		List<Result> results = bean.fetchByNull();
		
		if (results.size() > 0) {
			return Response.ok(results).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find by result by id.
	 *
	 * @param idc the idc
	 * 
	 * @return result (200) - successfully found result
	 * @return (404) - not found any result
	 */
	@GET
	@Path("/{idc}")
	public Response findById(@PathParam("idc") long idc) {
		Result result = bean.find(idc);
		
		if (result != null) {
			return Response.ok(result).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find results by swimmer, discipline and competition.
	 *
	 * @param swimmerIdc     the swimmer idc
	 * @param disciplineIdc  the discipline idc
	 * @param competitionIdc the competition idc
	 * 
	 * @return list of results (200) - successfully found results
	 * @return (404) - not found any result
	 */
	@GET
	@Path("/fetch/all")
	public Response getByAll(
			@QueryParam("swimmerIdc") Long swimmerIdc,
			@QueryParam("disciplineIdc") Long disciplineIdc,
			@QueryParam("competitonIdc") Long competitionIdc
	) {
		List<Result> results = bean.fetchByAll(swimmerIdc, disciplineIdc, competitionIdc);
		
		if (results.size() > 0) {
			return Response.ok(results).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find results by swimmer.
	 *
	 * @param idc the idc
	 * 
	 * @return list of results (200) - successfully found results
	 * @return (404) - not found any result
	 */
	@GET
	@Path("/fetch/swimmer/{idc}")
	public Response getBySwimmer(@PathParam("idc") Long idc) {
		List<Result> results = bean.fetchBySwimmerIdc(idc);
		
		if (results.size() > 0) {
			return Response.ok(results).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find results by discipline.
	 *
	 * @param idc the idc
	 * 
	 * @return list of results (200) - successfully found results
	 * @return (404) - not found any result
	 */
	@GET
	@Path("/fetch/discipline/{idc}")
	public Response getByDiscipline(@PathParam("idc") Long idc) {
		List<Result> results = bean.fetchByDisciplineIdc(idc);
		
		if (results.size() > 0) {
			return Response.ok(results).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Find results by competition.
	 *
	 * @param idc the idc
	 * 
	 * @return list of results (200) - successfully found results
	 * @return (404) - not found any result
	 */
	@GET
	@Path("/fetch/competition/{idc}")
	public Response getByCompetition(@PathParam("idc") Long idc) {
		List<Result> results = bean.fetchCompetitonIdc(idc);
		
		if (results.size() > 0) {
			return Response.ok(results).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Update result.
	 *
	 * @param resultDto the result dto
	 * @param idc       the idc
	 * 
	 * @return (204) - successfully updated result
	 * @return (400) - bad request
	 * @return (404) - not found any result
	 */
	
	@PUT
	@Path("/{idc}")
	public Response update(ResultDto resultDto, @PathParam("idc") long idc) {
		bean.update(resultDto, idc);
		return Response.noContent().build();
	}


	/**
	 * Delete result.
	 *
	 * @param idc the idc
	 * 
	 * @return (204) - successfully remove result
	 * @return (404) - not found any result
	 */
	@DELETE
	@Path("/{idc}")
	public Response delete(@PathParam("idc") long idc) {
		bean.delete(idc);
		return Response.noContent().build();
	}
}
