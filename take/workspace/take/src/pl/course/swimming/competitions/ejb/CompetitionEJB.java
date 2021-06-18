package pl.course.swimming.competitions.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.course.swimming.competitions.model.Competition;

@Stateless
public class CompetitionEJB {

	@PersistenceContext(name="competition")
	EntityManager manager;
	
	public void create(Competition competition) {
		manager.persist(competition);
	}

	public void delete(long idc) {
		Competition competition = manager.find(Competition.class, idc);
		manager.remove(competition);
	}

	public List<Competition> findByName(String name) {
		Query q = manager.createQuery("select c from Competition c where c.name = :name");
		q.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<Competition> list = q.getResultList();
		System.out.println(list.toString());
		return list;
	}

	public Competition find(long idc) {
		return manager.find(Competition.class, idc);
	}

	public List<Competition> get() {
		Query q = manager.createQuery("select c from Competition c");
		@SuppressWarnings("unchecked")
		List<Competition> list = q.getResultList();
		return list;
	}

	public void update(Competition competition) {
		competition = manager.merge(competition);
	}
}
