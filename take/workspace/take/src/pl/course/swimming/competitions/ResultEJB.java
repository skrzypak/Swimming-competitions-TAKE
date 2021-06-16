package pl.course.swimming.competitions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.course.swimming.competitions.exceptions.IdNotFoundException;

@Stateless
public class ResultEJB {

	@PersistenceContext(name="result")
	EntityManager manager;
	
	public void create(ResultDto resultDto) {
		
		Result result = new Result();
		
		Swimmer swimmer = manager.find(Swimmer.class, resultDto.getSwimmer_id());
		Discipline discipline = manager.find(Discipline.class, resultDto.getDiscipline_id());
		Competition competition = manager.find(Competition.class, resultDto.getCompetition_id());
		
		if(swimmer == null ) {
			Long id = new Long(resultDto.getSwimmer_id());
			throw new IdNotFoundException(id, "SWIMMER");
		}
		
		if(discipline == null ) {
			Long id = new Long(resultDto.getDiscipline_id());
			throw new IdNotFoundException(id, "DISCIPLINE");
		}
		
		if(competition == null ) {
			Long id = new Long(resultDto.getCompetition_id());
			throw new IdNotFoundException(id, "COMPETITION");
		}
		
		result.setTimeObtainedSeconds(resultDto.getTimeObtainedSeconds());
		result.setPlace(resultDto.getPlace());
		result.setSwimmer(swimmer);
		result.setDiscipline(discipline);
		result.setCompetition(competition);
		
		manager.persist(result);
	}

	public void delete(long idc) {
		Result result = manager.find(Result.class, idc);
		
		if (result == null) throw new IdNotFoundException(idc, "RESULT");
		
		manager.remove(result);
	}

	public Result find(long idc) {
		Result r = manager.find(Result.class, idc);
		return r;
	}

	// TODO: Incorrect return up 1 (only ID)
	
	public List<Result> get() {
		Query q = manager.createQuery("select r from Result r");
		@SuppressWarnings("unchecked")
		List<Result> list = q.getResultList();
		
		return list;
	}
	
	public List<Result> getAllResultsBySwimmer(long idc) {
		Query q = manager.createQuery("select r from Result r INNER JOIN r.swimmer s where s.idc = :idc");
		q.setParameter("idc", idc);
		
		@SuppressWarnings("unchecked")
		List<Result> list = q.getResultList();
		return list;
	}
	
	public List<Result> getAllResultsByCompetition(long idc) {
		Query q = manager.createQuery("select r from Result r INNER JOIN r.competition c where c.idc = :idc");
		q.setParameter("idc", idc);
		
		@SuppressWarnings("unchecked")
		List<Result> list = q.getResultList();
		return list;
	}

	public void update(Result result) {
		result = manager.merge(result);
	}
}
