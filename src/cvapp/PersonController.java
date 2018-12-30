package cvapp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean(name = "person")
@SessionScoped
public class PersonController {
	
	@EJB
	PersonManager mgr;
	
	private String searchQuery;
	
	private List<Person> queryResult = new ArrayList<Person>();
	
	@PostConstruct
    public void init() {
        System.out.println("Create " + this);
        if (mgr.findAll().size() == 0) {
        	System.out.println("Je fais ça");
            Person p1 = new Person();
            p1.setFirstname("Loic");
            p1.setLastname("Drouard");
            p1.setEmail("loic.drouard@free.fr");
            p1.setPassword("llllllllllllll");
            mgr.save(p1);
            
            Person p2 = new Person();
            p2.setFirstname("Lina");
            p2.setLastname("Benhamza");
            p2.setEmail("lina.benhamza@free.fr");
            p2.setPassword("llllllllllllll");
            mgr.save(p2);
        }
        
    }
	
	public Person savePerson(Person person) {
		return mgr.save(person);
	}
	
	public Person getPerson(Long id) {
		return mgr.find(id);
	}
	
	public void findByName(AjaxBehaviorEvent event){
		queryResult = mgr.findPersonsByName(searchQuery, 8);
		System.out.println(queryResult.size());
    }
	
	public List<Person> getPersons() {
		return mgr.findAll();
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List<Person> getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(List<Person> queryResult) {
		this.queryResult = queryResult;
	}
	
}
