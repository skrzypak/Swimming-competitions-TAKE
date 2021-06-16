package pl.course.swimming.competitions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.course.swimming.competitions.exceptions.DuplicatException;

@Stateless
public class DisciplineEJB {

	@PersistenceContext(name="discipline")
	EntityManager manager;
	
	public void create(Discipline discipline) {
		
		Discipline tmp = this.findByName(discipline.getName());
		
		if(tmp == null) {
			manager.persist(discipline);
		} else {
			throw new DuplicatException("NAME", discipline.getName());
		}
	}

	public void delete(long idc) {
		Discipline discipline = manager.find(Discipline.class, idc);
		manager.remove(discipline);
	}

	/**
	 * @return discipline instance if found otherwise NULL
	 * */
	public Discipline findByName(String name) {
		Query q = manager.createQuery("select d from Discipline d where d.name = :name");
		q.setParameter("name", name);
		
		@SuppressWarnings("unchecked")
		List<Discipline> element = q.getResultList();
		
		return element.size() > 0 ? element.get(0) : null;
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
