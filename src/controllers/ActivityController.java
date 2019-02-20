package controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;

import beans.Activity;
import beans.Person;
import dao.ActivityManager;

@ManagedBean(name = "activity")
@SessionScoped
public class ActivityController {

	@EJB
	ActivityManager mgr;
	
	private Activity theActivity = new Activity();
	
	private LazyDataModel<Activity> lazyModel;
	
	@PostConstruct
    public void init() {
		System.out.println("init activity controller");
		
	}

	public Activity saveActivity(Activity activity) {
		return mgr.save(activity);
	}
	
	public Activity getActivity(Long id) {
		return mgr.find(id);
	}
	
	public void deleteActivity(Activity activity) {
		mgr.delete(activity);
	}
	
	public void deleteTheActivity() {
		mgr.delete(theActivity);
		theActivity = new Activity();
	}
	
	public Activity getTheActivity() {
		return theActivity;
	}

	public void editActivity(Long id) {
		theActivity = getActivity(id);
	}
	
	public void setTheActivity(Activity theActivity) {
		this.theActivity = theActivity;
	}
	
	public void saveTheActivity(Person person) {
		theActivity.setPerson(person);
		saveActivity(theActivity);
	}
	
	public void initTheActivity(String type) {
		this.theActivity = new Activity();
		theActivity.setType(type);
	}

	public LazyDataModel<Activity> getLazyModel() {
		return lazyModel;
	}
	
	public void setLazyModel(LazyDataModel<Activity> lazyModel) {
		this.lazyModel = lazyModel;
	}
}
