package pl.course.swimming.competitions;

import java.util.LinkedList;
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
	
	public List<Result> fetchByAll(Long swimmerIdc, Long disciplineIdc, Long competitionIdc)
	{
		return this.fetch(swimmerIdc, disciplineIdc, competitionIdc);
	}
	
	public List<Result> fetchBySwimmerIdc(Long idc) {
		return this.fetch(idc, null, null);
	}

	public List<Result> fetchByDisciplineIdc(Long idc) {
		return this.fetch(null, idc, null);
	}

	public List<Result> fetchCompetitonIdc(Long idc) {
		return this.fetch(null, null, idc);
	}
	
	private List<Result> fetch(Long swimmerIdc, Long disciplineIdc, Long competitionIdc) {
		
		StringBuffer query = new StringBuffer("select r.idc from Result r");
		
		if (swimmerIdc != null) {
			query.append(" INNER JOIN r.swimmer s on s.idc = " + swimmerIdc);
		}
		
		if (disciplineIdc != null) {
			query.append(" INNER JOIN r.discipline d on d.idc = " + disciplineIdc);
		}
		
		if (competitionIdc != null) {
			query.append( " INNER JOIN r.competition c on c.idc = " + competitionIdc);
		}
		
		Query q = manager.createQuery(query.toString(), Long.class);
		
		@SuppressWarnings("unchecked")
		List<Long> idcs = q.getResultList();
		
		List<Result> list = new LinkedList<Result>();
		
		for (Long idc : idcs) {
			Result result = manager.getReference(Result.class, idc);
			list.add(new Result(result));
		}
		
		return list;
	}
	
	public Result find(long idc) {
		Result r = manager.find(Result.class, idc);
		return r;
	}

	public void delete(long idc) {
		Result result = manager.find(Result.class, idc);
		
		if (result == null) throw new IdNotFoundException(idc, "RESULT");
		
		manager.remove(result);
	}
	
	public void update(Result result) {
		result = manager.merge(result);
	}
}
