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

@Path("/discipline")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class DisciplineREST {
	
	@EJB
	DisciplineEJB bean;

	@POST
	public String create(Discipline discipline) {
		bean.create(discipline);
		return "discipline created!";
	}
	
	@GET
	public List<Discipline> get() {
		List<Discipline> disciplines = bean.get();
		return disciplines;
	}

	@GET
	@Path("/{idc}")
	public Discipline findById(@PathParam("idc") long idc) {
		Discipline discipline = bean.find(idc);
		return discipline;
	}
	
	@GET
	@Path("/name")
	public Discipline findByName(@QueryParam("name") String name) {
		Discipline discipline = bean.findByName(name);
		return discipline;
	}
	

	@PUT
	public String update(Discipline discipline) {
		try {
			bean.update(discipline);
			return "discipline updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "discipline not updated :(";
		}
	}


	@DELETE
	@Path("/{idc}")
	public void delete(@PathParam("idc") long idc) {
		bean.delete(idc);
	}
}
