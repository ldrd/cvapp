package cvapp;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PersonManager {
	
	@PersistenceContext(unitName = "dataCV")
    EntityManager em;
	
	
	@PostConstruct
	public void init() { System.out.println("Init PersonManager"); }
	
	public Person find(Long id) {
		return em.find(Person.class, id);
	}
	
	public Person save(Person person) {
		System.out.println(person.toString());
		if (person.getId() == null)
			em.persist(person);
		else
			person = em.merge(person);
		return person;
	}
	
	public void delete(Person person) {
		person = save(person);
		em.remove(person);
	}
	
	public List<Person> findPersonsByName(String name) {
		String[] strs = name.split(" ");
		StringBuilder str = new StringBuilder();
		String q = "Select p from Person p where firstname like %:str% or lastname like %:str%";
		
		if (strs.length > 0)
			str.append(q);
		for (int i = 1; i < strs.length; i++) {
			str.append(" intersect ");
			str.append(q);
		}
		
		TypedQuery<Person> query = em.createQuery(str.toString(), Person.class);
		query.setParameter("str", str);
		return query.getResultList();
	}

	public List<Person> findAll() {
		return em.createQuery("Select p From Person p", Person.class).getResultList();
	}
}
