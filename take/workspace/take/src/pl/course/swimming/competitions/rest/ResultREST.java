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

import pl.course.swimming.competitions.dto.ResultDto;
import pl.course.swimming.competitions.ejb.ResultEJB;
import pl.course.swimming.competitions.model.Result;

/**
 * Result API
 * @version 1.0
 * @category REST
 * */
@Provider
@Path("/result")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ResultREST {
	
	@EJB
	ResultEJB bean;

	@POST
	public Response create(ResultDto resultDto) {
		bean.create(resultDto);
		return Response.ok().build();
	}

	@GET
	@Path("/{idc}")
	public Response findById(@PathParam("idc") long idc) {
		Result result = bean.find(idc);
		
		if (result != null) {
			return Response.ok(result).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("/fetch/3")
	public Response get(
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
	
	@GET
	@Path("/fetch/swimmer/{idc}")
	public Response getBySwimmer(@PathParam("idc") Long idc) {
		List<Result> results = bean.fetchBySwimmerIdc(idc);
		
		if (results.size() > 0) {
			return Response.ok(results).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("/fetch/discipline/{idc}")
	public Response getByDiscipline(@PathParam("idc") Long idc) {
		List<Result> results = bean.fetchByDisciplineIdc(idc);
		
		if (results.size() > 0) {
			return Response.ok(results).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("/fetch/competition/{idc}")
	public Response getByCompetition(@PathParam("idc") Long idc) {
		List<Result> results = bean.fetchCompetitonIdc(idc);
		
		if (results.size() > 0) {
			return Response.ok(results).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("/{idc}")
	public Response update(ResultDto resultDto, @PathParam("idc") long idc) {
		bean.update(resultDto, idc);
		return Response.noContent().build();
	}


	@DELETE
	@Path("/{idc}")
	public Response delete(@PathParam("idc") long idc) {
		bean.delete(idc);
		return Response.noContent().build();
	}
}
