package pl.course.swimming.competitions.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.course.swimming.competitions.exceptions.IdNotFoundException;
import pl.course.swimming.competitions.model.Competition;

/**
 * Competition service
 * @version 1.0
 * @category EJB
 * @see pl.course.swimming.competitions.rest.CompetitionREST
 * */
@Stateless
public class CompetitionEJB {

	@PersistenceContext(name="competition")
	EntityManager manager;
	
	/**
	 * Add new competition
	 * @param competition POJO object
	 * */
	public void create(Competition competition) {
		manager.persist(competition);
	}

	/**
	 * Remove competition with proper id
	 * @param idc id of competition
	 * */
	public void delete(long idc) {
		Competition competition = manager.find(Competition.class, idc);
		
		if(competition == null) {
			throw new IdNotFoundException(idc, "COMPETITION");
		}
		
		manager.remove(competition);
	}

	/**
	 * Get competitions by name
	 * @param name name of competition
	 * @return list of competitions which have same name as passed argument
	 * */
	public List<Competition> findByName(String name) {
		Query q = manager.createQuery("select c from Competition c where c.name = :name");
		q.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<Competition> list = q.getResultList();
		return list;
	}

	/**
	 * Get competition by id
	 * @param idc id of competition
	 * @return competition which has same id as passed argument
	 * */
	public Competition find(long idc) {
		return manager.find(Competition.class, idc);
	}

	/**
	 * Get all competitions
	 * @return list of competitions
	 * */
	public List<Competition> get() {
		Query q = manager.createQuery("select c from Competition c");
		@SuppressWarnings("unchecked")
		List<Competition> list = q.getResultList();
		return list;
	}

	/**
	 * Update competition
	 * @param competition competition POJO object
	 * */
	public void update(Competition competition) {
		
		Long idc = competition.getIdc();
		
		try {
			this.find(idc);
		} catch(RuntimeException ignore) {
			throw new IdNotFoundException(idc, "COMPETITION");
		}
		
		competition = manager.merge(competition);
	}
}
