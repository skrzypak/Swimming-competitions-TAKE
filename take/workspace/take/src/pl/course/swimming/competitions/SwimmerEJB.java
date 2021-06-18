package pl.course.swimming.competitions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SwimmerEJB {

	@PersistenceContext(name="swimmer")
	EntityManager manager;
	
	public void create(Swimmer swimmer) {
		manager.persist(swimmer);
	}

	public void delete(long idc) {
		Swimmer swimmer = manager.find(Swimmer.class, idc);
		manager.remove(swimmer);
	}

	public List<Swimmer> findByFullName(String name, String surname) {
		Query q = manager.createQuery("select s from Swimmer s where s.name = :name and s.surname = :surname");
		q.setParameter("name", name);
		q.setParameter("surname", surname);
		@SuppressWarnings("unchecked")
		List<Swimmer> list = q.getResultList();
		return list;
	}

	public Swimmer find(long idc) {
		return manager.find(Swimmer.class, idc);
	}

	public List<Swimmer> get() {
		Query q = manager.createQuery("select s from Swimmer s");
		@SuppressWarnings("unchecked")
		List<Swimmer> list = q.getResultList();
		return list;
	}

	public void update(Swimmer swimmer) {
		swimmer = manager.merge(swimmer);
	}
}
