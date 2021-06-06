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
	public List<Result> get() {
		List<Result> results = bean.get();
		return results;
	}

	@GET
	@Path("/{idc}")
	public Result findById(@PathParam("idc") long idc) {
		Result result = bean.find(idc);
		return result;
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
