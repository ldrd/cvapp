package dao;

import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import beans.Person;

@Stateless
public class PersonManager {

	@PersistenceContext(unitName = "dbCV")
    private EntityManager em;
	
	@PostConstruct
    public void init() {
        System.out.println("init " + this + " with " + em);
    }
	
	public Person addPerson(Person p) {
		em.persist(p);
		return p;
	}
	
	public Person getPerson(Long id) {
		return em.find(Person.class, id);
	}
	
	public Person updatePerson(Person p) {
		return em.merge(p);
	}
	
	public void removePerson(Person p) {
		em.remove(p);
	}
	
	public Set<Person> findByName(String name) {
		Set<Person> result = new TreeSet<>();
		String[] strs = name.split(" ");
		for (String str : strs) {
			TypedQuery<Person> query = 
					em.createQuery("Select * from Person where firstname like %:str% or lastname like %:str%",
									Person.class);
			query.setParameter("str", str);
			result.addAll(query.getResultList());
		}
		return result;
	}
}
