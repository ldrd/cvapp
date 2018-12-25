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

	@PersistenceContext(unitName = "dbCV")
    private EntityManager em;
	
	@PostConstruct
    public void init() {
        System.out.println("init " + this + " with " + em);
    }
	
	public Activity addActivity(Activity a) {
		em.persist(a);
		return a;
	}
	
	public Activity getActivity(Long id) {
		return em.find(Activity.class, id);
	}
	
	public Activity updateActivity(Activity a) {
		return em.merge(a);
	}
	
	public void removeActivity(Activity a) {
		em.remove(a);
	}
	
	private List<Activity> findByPerson(Long idPerson) {
		TypedQuery<Activity> query = em.createQuery("Select * from Activity where person = :idPerson", Activity.class);
		query.setParameter("idPerson", idPerson);
		return query.getResultList();
	}
}
