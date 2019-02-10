package dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import beans.Activity;

@Stateless
public class ActivityManager {
	
	@PersistenceContext(unitName = "cvdata")
    EntityManager em;
	
	@PostConstruct
    public void init() {
		System.out.println("init activity manager");
	}
	
	public Activity find(Long id) {
		return em.find(Activity.class, id);
	}
	
	public Activity save(Activity activity) {
		if (activity.getId() == null)
			em.persist(activity);
		else
			activity = em.merge(activity);
		return activity;
	}
	
	public void delete(Activity activity) {
		activity = save(activity);
		em.remove(activity);
	}
	
	public List<Activity> findActivitiesByName(String name, int first, int pageSize) {
		String q = 	"select a from Activity a " +
				"where lower(a.title) like lower(:name)";
		TypedQuery<Activity> query = em.createQuery(q, Activity.class);
		query.setFirstResult(first);
		if (pageSize > 0)
			query.setMaxResults(pageSize);
		query.setParameter("name", "%" + name + "%");
		
		return query.getResultList();
	}

	public Long countActivitiesByName(String searchQuery) {
		String q = 	"select count(a) from Activity a " +
				"where lower(a.title) like lower(:name)";
		TypedQuery<Long> query = em.createQuery(q, Long.class);
		query.setParameter("name", "%" + searchQuery + "%");
		
		return query.getSingleResult();
	}
}
