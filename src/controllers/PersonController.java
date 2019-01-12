package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import beans.Person;
import dao.PersonManager;

@ManagedBean(name = "person")
@SessionScoped
public class PersonController {
	
	@EJB
	PersonManager mgr;
	
	private String searchQuery;
	private List<Person> queryResult = new ArrayList<Person>();
	
	private LazyDataModel<Person> lazyModel;
	private Person thePerson = new Person();
	
	@ManagedProperty(value="#{activity}")
	private ActivityController activityController;
	
	@PostConstruct
    public void init() {
        System.out.println("Create " + this);
        if (mgr.findAll().size() == 0) {
        	System.out.println("Je fais ça");
        	Person p = new Person();
            p.setFirstname("test");
            p.setLastname("test");
            p.setEmail("test@free.fr");
            p.setPassword("1");
            mgr.save(p);
        	
            Person p1 = new Person();
            p1.setFirstname("Loic");
            p1.setLastname("Drouard");
            p1.setEmail("loic.drouard@free.fr");
            p1.setPassword("12");
            mgr.save(p1);
            
            Person p2 = new Person();
            p2.setFirstname("Lina");
            p2.setLastname("Benhamza");
            p2.setEmail("lina.benhamza@free.fr");
            p2.setPassword("123");
            mgr.save(p2);
        }
        setLazyModel(new LazyDataModel<Person>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<Person> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Person> persons = mgr.findPersonsByName(searchQuery, first, pageSize);
                lazyModel.setRowCount(persons.size());
                return persons;
            }
        
            
        });
    }
	
	public Person savePerson(Person person) {
		return mgr.save(person);
	}
	
	public Person getPerson(Long id) {
		return mgr.find(id);
	}
	
	public void findByName() {
		queryResult = mgr.findPersonsByName(searchQuery);
	}
	
	public void findByName(AjaxBehaviorEvent event){
		queryResult = mgr.findPersonsByName(searchQuery, 0, 8);
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
	
	public String cv(Long id) {
		setThePerson(mgr.find(id));
		return "cv?faces-redirect=true";
	}

	public Person getThePerson() {
		return thePerson;
	}

	public void setThePerson(Person thePerson) {
		this.thePerson = thePerson;
	}
	
	public String saveThePerson() {
		savePerson(thePerson);
		return "cv?faces-redirect=true";
	}
	
	public void setActivityController(ActivityController ac) {
		this.activityController = ac;
	}
	
	public String saveActivity() {
		thePerson.addActivity(activityController.getTheActivity());
		return activityController.saveTheActivity(thePerson);
	}

	public LazyDataModel<Person> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Person> lazyModel) {
		this.lazyModel = lazyModel;
	}
	
	
}
