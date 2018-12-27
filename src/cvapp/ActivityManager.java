package cvapp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ActivityManager {
	
	@PersistenceContext(unitName = "dataCV")
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
	
	public List<Activity> findActivitiesByPerson(Long idPerson) {
		TypedQuery<Activity> query = em.createQuery("Select * from Activity where person = :idPerson", Activity.class);
		query.setParameter("idPerson", idPerson);
		return query.getResultList();
	}
}
