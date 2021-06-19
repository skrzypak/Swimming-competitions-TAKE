package pl.course.swimming.competitions.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.course.swimming.competitions.exceptions.DuplicateException;
import pl.course.swimming.competitions.exceptions.IdNotFoundException;
import pl.course.swimming.competitions.model.Discipline;

/**
 * Discipline service
 * @version 1.0
 * @category EJB
 * @see pl.course.swimming.competitions.rest.DisciplineREST
 * */
@Stateless
public class DisciplineEJB {

	@PersistenceContext(name="discipline")
	EntityManager manager;
	
	/**
	 * Add new discipline
	 * @param discipline POJO object
	 * */
	public void create(Discipline discipline) {
		
		Discipline tmp = this.findByName(discipline.getName());
		
		if(tmp == null) {
			manager.persist(discipline);
		} else {
			throw new DuplicateException("NAME", discipline.getName());
		}
	}

	/**
	 * Remove discipline with proper id
	 * @param idc id of discipline
	 * */
	public void delete(long idc) {
		Discipline discipline = manager.find(Discipline.class, idc);
		
		if(discipline == null) {
			throw new IdNotFoundException(idc, "DISCIPLINE");
		}
		
		manager.remove(discipline);
	}

	/**
	 * Get disciplines by name
	 * @param name unique name of discipline
	 * @return discipline instance if found otherwise NULL
	 * */
	public Discipline findByName(String name) {
		Query q = manager.createQuery("select d from Discipline d where d.name = :name");
		q.setParameter("name", name);
		
		@SuppressWarnings("unchecked")
		List<Discipline> element = q.getResultList();
		
		return element.size() > 0 ? element.get(0) : null;
	}

	/**
	 * Get discipline by id
	 * @param idc id of discipline
	 * @return discipline which has same id as passed argument
	 * */
	public Discipline find(long idc) {
		return manager.find(Discipline.class, idc);
	}

	/**
	 * Get all disciplines
	 * @return list of disciplines
	 * */
	public List<Discipline> get() {
		Query q = manager.createQuery("select d from Discipline d");
		@SuppressWarnings("unchecked")
		List<Discipline> list = q.getResultList();
		return list;
	}

	/**
	 * Update discipline
	 * @param discipline discipline POJO object
	 * */
	public void update(Discipline discipline) {
		
		Long idc = discipline.getIdc();
		
		try {
			this.find(idc);
		} catch(RuntimeException ignore) {
			throw new IdNotFoundException(idc, "DISCIPLINE");
		}
		
		Discipline tmp = this.findByName(discipline.getName());
		
		if(tmp == null) {
			discipline = manager.merge(discipline);
		} else {
			throw new DuplicateException("NAME", discipline.getName());
		}
		
	}
}
