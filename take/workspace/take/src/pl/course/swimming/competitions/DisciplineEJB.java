package pl.course.swimming.competitions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DisciplineEJB {

	@PersistenceContext(name="discipline")
	EntityManager manager;
	
	public void create(Discipline discipline) {
		manager.persist(discipline);
	}

	public void delete(long idc) {
		Discipline discipline = manager.find(Discipline.class, idc);
		manager.remove(discipline);
	}

	public Discipline findByName(String name) {
		return manager.find(Discipline.class, name);
	}

	public Discipline find(long idc) {
		return manager.find(Discipline.class, idc);
	}

	public List<Discipline> get() {
		Query q = manager.createQuery("select d from Discipline d");
		@SuppressWarnings("unchecked")
		List<Discipline> list = q.getResultList();
		return list;
	}

	public void update(Discipline discipline) {
		discipline = manager.merge(discipline);
	}
}
