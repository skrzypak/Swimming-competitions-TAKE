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

@Path("/swimmer")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class SwimmerREST {
	
	@EJB
	SwimmerEJB bean;

	@POST
	public String create(Swimmer swimmer) {
		bean.create(swimmer);
		return "swimmer created!";
	}
	
	@GET
	public List<Swimmer> get() {
		
		List<Swimmer> swimmers = bean.get();
		return swimmers;
	}

	@GET
	@Path("/{idc}")
	public Swimmer findById(@PathParam("idc") long idc) {
		Swimmer swimmer = bean.find(idc);
		return swimmer;
	}
	
	@GET
	@Path("/{name}&{surname}")
	public List<Swimmer> findAllByFullName(
			@PathParam("name") String name,
			@PathParam("surname") String surname) {
		List<Swimmer> swimmers = bean.findAllByFullName(name, surname);
		return swimmers;
	}
	

	@PUT
	public String update(Swimmer swimmer) {
		try {
			bean.update(swimmer);
			return "swimmer updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "swimmer not updated :(";
		}
	}


	@DELETE
	@Path("/{idc}")
	public void delete(@PathParam("idc") long idc) {
		bean.delete(idc);
	}
}
