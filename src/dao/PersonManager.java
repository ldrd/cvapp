package dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import beans.Person;

@Stateless
public class PersonManager {
	
	@PersistenceContext(unitName = "cvdata")
    EntityManager em;
	
	
	@PostConstruct
	public void init() { System.out.println("Init PersonManager"); }
	
	public Person find(Long id) {
		return em.find(Person.class, id);
	}
	
	public Person save(Person person) {
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
		return findPersonsByName(name, 0, 0);
	}
	
	public List<Person> findPersonsByEmail(String email) {
		String q = "Select p From Person p where email = :email";
		TypedQuery<Person> query = em.createQuery(q, Person.class);
		query.setParameter("email", email);
		return query.getResultList();
	}

	public List<Person> findAll() {
		return em.createQuery("Select p From Person p", Person.class).getResultList();
	}

	public List<Person> findPersonsByName(String name, int first, int pageSize) {
		System.out.println(first + " " + pageSize);
		String q = 	"select p from Person p " +
					"where lower(concat(p.lastname,' ',p.firstname)) like lower(:name)" +
					" or lower(concat(p.firstname,' ',p.lastname)) like lower(:name)";
		TypedQuery<Person> query = em.createQuery(q, Person.class);
		query.setFirstResult(first);
		if (pageSize > 0)
			query.setMaxResults(pageSize);
		query.setParameter("name", "%" + name + "%");
		
		return query.getResultList();
	}
}
