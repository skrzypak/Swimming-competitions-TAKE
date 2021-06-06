package pl.course.swimming.competitions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ResultEJB {

	@PersistenceContext(name="result")
	EntityManager manager;
	
	public void create(ResultDto resultDto) {
		
		Result result = new Result();
		
		Swimmer swimmer = manager.find(Swimmer.class, resultDto.getSwimmer_id());
		Discipline discipline = manager.find(Discipline.class, resultDto.getDiscipline_id());
		Competition competition = manager.find(Competition.class, resultDto.getCompetition_id());
		
		result.setTimeObtainedSeconds(resultDto.getTimeObtainedSeconds());
		result.setPlace(resultDto.getPlace());
		result.setSwimmer(swimmer);
		result.setDiscipline(discipline);
		result.setCompetition(competition);
		
		manager.persist(result);
	}

	public void delete(long idc) {
		Result result = manager.find(Result.class, idc);
		manager.remove(result);
	}

	public Result find(long idc) {
		Result r = manager.find(Result.class, idc);
		return r;
	}

	public List<Result> get() {
		Query q = manager.createQuery("select r from Result r");
		@SuppressWarnings("unchecked")
		List<Result> list = q.getResultList();
		return list;
	}

	public void update(Result result) {
		result = manager.merge(result);
	}
}
