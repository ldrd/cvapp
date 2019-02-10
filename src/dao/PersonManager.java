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
	
	public Long countPersons() {
		String q = 	"select count(p) from Person p";
		TypedQuery<Long> query = em.createQuery(q, Long.class);
		return query.getSingleResult();
	}
	
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
	
	public Person findPersonByEmail(String email) {
		String q = "Select p From Person p where email = :email";
		TypedQuery<Person> query = em.createQuery(q, Person.class);
		query.setParameter("email", email);
		List<Person> result = query.getResultList();
		return result.size() == 1 ? result.get(0) : null;
	}

	public List<Person> findAll() {
		return em.createQuery("Select p From Person p", Person.class).getResultList();
	}
	
	public List<Person> findPersonsByName(String name, int first, int pageSize) {
		String q = 	"select p from Person p " +
					"where lower(concat(p.lastname,' ',p.firstname)) like lower(:name)" +
					" or lower(concat(p.firstname,' ',p.lastname)) like lower(:name)";
		TypedQuery<Person> query = em.createQuery(q, Person.class);
		query.setParameter("name", "%" + name + "%");
		
		if (pageSize > 0)
			query.setMaxResults(pageSize);
		query.setFirstResult(first);
		
		return query.getResultList();
	}

	public Long countPersonsByName(String name) {
		String q = 	"select count(p) from Person p " +
				"where lower(concat(p.lastname,' ',p.firstname)) like lower(:name)" +
				" or lower(concat(p.firstname,' ',p.lastname)) like lower(:name)";
		TypedQuery<Long> query = em.createQuery(q, Long.class);
		query.setParameter("name", "%" + name + "%");
		
		return query.getSingleResult();
	}
}
