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

@Path("/competition")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class CompetitionREST {
	
	@EJB
	CompetitionEJB bean;

	@POST
	public String create(Competition competition) {
		bean.create(competition);
		return "competition created!";
	}
	
	@GET
	public List<Competition> get() {
		List<Competition> competitions = bean.get();
		return competitions;
	}

	@GET
	@Path("/{idc}")
	public Competition findById(@PathParam("idc") long idc) {
		Competition competition = bean.find(idc);
		return competition;
	}
	
	@GET
	@Path("/{name}")
	public List<Competition> findAllByName(@PathParam("name") String name) {
		List<Competition> competitions = bean.findAllByName(name);
		return competitions;
	}
	

	@PUT
	public String update(Competition competition) {
		try {
			bean.update(competition);
			return "competition updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "competition not updated :(";
		}
	}


	@DELETE
	@Path("/{idc}")
	public void delete(@PathParam("idc") long idc) {
		bean.delete(idc);
	}
}
