package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.Activity;

@Stateless
public class ActivityManager {
	
	@PersistenceContext(unitName = "cvdata")
    EntityManager em;
	
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
}
