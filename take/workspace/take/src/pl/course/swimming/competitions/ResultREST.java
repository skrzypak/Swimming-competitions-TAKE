package pl.course.swimming.competitions;

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

@Path("/result")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ResultREST {
	
	@EJB
	ResultEJB bean;

	@POST
	public String create(ResultDto resultDto) {
		bean.create(resultDto);
		return "result created!";
	}

	@GET
	@Path("/{idc}")
	public Result findById(@PathParam("idc") long idc) {
		Result result = bean.find(idc);
		return result;
	}
	
	@GET
	@Path("/fetch/3")
	public List<Result> get(
			@QueryParam("swimmerIdc") Long swimmerIdc,
			@QueryParam("disciplineIdc") Long disciplineIdc,
			@QueryParam("competitonIdc") Long competitionIdc
	) {
		List<Result> results = bean.fetchByAll(swimmerIdc, disciplineIdc, competitionIdc);
		return results;
	}
	
	@GET
	@Path("/fetch/swimmer/{idc}")
	public List<Result> getBySwimmer(@PathParam("idc") Long idc) {
		List<Result> results = bean.fetchBySwimmerIdc(idc);
		return results;
	}
	
	@GET
	@Path("/fetch/discipline/{idc}")
	public List<Result> getByDiscipline(@PathParam("idc") Long idc) {
		List<Result> results = bean.fetchByDisciplineIdc(idc);
		return results;
	}
	
	@GET
	@Path("/fetch/competition/{idc}")
	public List<Result> getByCompetition(@PathParam("idc") Long idc) {
		List<Result> results = bean.fetchCompetitonIdc(idc);
		return results;
	}
	
	@PUT
	public String update(Result result) {
		try {
			bean.update(result);
			return "result updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "result not updated :(";
		}
	}


	@DELETE
	@Path("/{idc}")
	public void delete(@PathParam("idc") long idc) {
		bean.delete(idc);
	}
}
