package controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import beans.Activity;
import beans.Person;

@ManagedBean(name = "search")
@SessionScoped
public class SearchController {

	@ManagedProperty(value="#{activity}")
	private ActivityController activityController;

	@ManagedProperty(value="#{person}")
	private PersonController personController;
	
	private String searchQuery;

	@PostConstruct
    public void init() {
		
		personController.setLazyModel(new LazyDataModel<Person>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<Person> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Person> persons = personController.mgr.findPersonsByName(searchQuery, first, pageSize);
                int countPersons = Math.toIntExact(personController.mgr.countPersonsByName(searchQuery));
                personController.getLazyModel().setRowCount(countPersons);
                return persons;
            }
        });
		
		activityController.setLazyModel(new LazyDataModel<Activity>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<Activity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Activity> activities = activityController.mgr.findActivitiesByName(searchQuery, first, pageSize);
                System.out.println(searchQuery);
                System.out.println(activities.size());
                int countActivities = Math.toIntExact(activityController.mgr.countActivitiesByName(searchQuery));
                activityController.getLazyModel().setRowCount(countActivities);
                return activities;
            }
        
            
        });
	}
	
	public void findPersonsByName(AjaxBehaviorEvent event){
		personController.findByName(searchQuery);
    }
	
	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	public void setActivityController(ActivityController activityController) {
		this.activityController = activityController;
	}

	public void setPersonController(PersonController personController) {
		this.personController = personController;
	}
	
}
