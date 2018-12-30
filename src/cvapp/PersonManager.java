package cvapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	
	//We could use intersect but hql doesn't allow it
	public List<Person> findPersonsByName(String name) {
		return findPersonsByName(name, 0);
	}

	public List<Person> findAll() {
		return em.createQuery("Select p From Person p", Person.class).getResultList();
	}

	public List<Person> findPersonsByName(String name, int limit) {
		List<Person> result = new ArrayList<Person>();
		String[] strs = name.split(" ");
		String q = "Select p from Person p where firstname like :str or lastname like :str";
		
		if (strs.length > 0) {
			TypedQuery<Person> query = em.createQuery(q, Person.class);
			query.setParameter("str", "%" + strs[0] + "%");
			result.addAll(query.getResultList());
		}
		
		for (int i = 1; i < strs.length; i++) {
			TypedQuery<Person> query = em.createQuery(q, Person.class);
			query.setParameter("str", "%" + strs[i] + "%");
			List<Person> tmp = query.getResultList();
			//intersect between result and tmp
			result = result.stream().filter(tmp::contains).collect(Collectors.toList());
		}
		if (limit > 0)
			result = result.subList(0, Math.min(8, result.size()));
		
		return result;
	}
}
