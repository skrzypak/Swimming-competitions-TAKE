package pl.course.swimming.competitions.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.course.swimming.competitions.exceptions.IdNotFoundException;
import pl.course.swimming.competitions.model.Swimmer;

/**
 * Swimmer service
 * @version 1.0
 * @category EJB
 * @see pl.course.swimming.competitions.rest.SwimmerREST
 * */
@Stateless
public class SwimmerEJB {

	@PersistenceContext(name="swimmer")
	EntityManager manager;
	
	/**
	 * Add new swimmer
	 * @param swimmer POJO object
	 * */
	public void create(Swimmer swimmer) {
		manager.persist(swimmer);
	}

	/**
	 * Remove swimmer with proper id
	 * @param idc id of swimmer
	 * */
	public void delete(long idc) {
		Swimmer swimmer = manager.find(Swimmer.class, idc);
		
		if(swimmer == null) {
			throw new IdNotFoundException(idc, "SWIMMER");
		}
		
		manager.remove(swimmer);
	}

	/**
	 * Get swimmers by its full name
	 * @param name name of swimmer
	 * @param surname surname of swimmer
	 * @return list of swimmers which have same name and surname as passed argument
	 * */
	public List<Swimmer> findByFullName(String name, String surname) {
		Query q = manager.createQuery("select s from Swimmer s where s.name = :name and s.surname = :surname");
		q.setParameter("name", name);
		q.setParameter("surname", surname);
		@SuppressWarnings("unchecked")
		List<Swimmer> list = q.getResultList();
		return list;
	}

	/**
	 * Get swimmer by id
	 * @param idc id of swimmer
	 * @return swimmer which has same id as passed argument
	 * */
	public Swimmer find(long idc) {
		return manager.find(Swimmer.class, idc);
	}

	/**
	 * Get all swimmers
	 * @return list of swimmers
	 * */
	public List<Swimmer> get() {
		Query q = manager.createQuery("select s from Swimmer s");
		@SuppressWarnings("unchecked")
		List<Swimmer> list = q.getResultList();
		return list;
	}

	/**
	 * Update swimmer
	 * @param swimmer swimmer POJO object
	 * */
	public void update(Swimmer swimmer) {
		
		Long idc = swimmer.getIdc();
		
		try {
			this.find(idc);
		} catch(RuntimeException ignore) {
			throw new IdNotFoundException(idc, "SWIMMER");
		}
		
		swimmer = manager.merge(swimmer);
	}
}
