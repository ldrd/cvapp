package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import beans.Activity;
import beans.Person;

@Stateless
public class DaoManager {

	@PersistenceContext(unitName = "dbCV")
    private EntityManager em;
	
	@PostConstruct
    public void init() {
        System.out.println("init " + this + " with " + em);
    }
	
	public <T> T add(T entity) {
	    em.persist(entity);
	    return entity;
	}

	public <T> T update(T entity) {
	    return em.merge(entity);
	}

	public <T> void remove(Class<T> clazz, Long pk) {
	    T entity = em.find(clazz, pk);
	    if (entity != null)
	        em.remove(entity);
	}
	
	public <T> T find(Class<T> clazz, Long id) {
		return em.find(clazz, id);
	}
	
	public List<Activity> findActivitiesByPerson(Long idPerson) {
		TypedQuery<Activity> query = em.createQuery("Select * from Activity where person = :idPerson", Activity.class);
		query.setParameter("idPerson", idPerson);
		return query.getResultList();
	}
	
	public List<Person> findByName(String name) {
		return null;
	}
}
