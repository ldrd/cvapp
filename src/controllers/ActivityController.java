package controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import beans.Activity;
import beans.Person;
import dao.ActivityManager;

@ManagedBean(name = "activity")
@SessionScoped
public class ActivityController {

	@EJB
	ActivityManager mgr;
	
	private Activity theActivity = new Activity();
	
	public Activity saveActivity(Activity activity) {
		return mgr.save(activity);
	}
	
	public Activity getActivity(Long id) {
		return mgr.find(id);
	}
	
	public void deleteActivity(Activity activity) {
		mgr.delete(activity);
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
	
	public String saveTheActivity(Person person) {
		theActivity.setPerson(person);
		saveActivity(theActivity);
		return "cv?faces-redirect=true";
	}
	
	public void initTheActivity(String type) {
		this.theActivity = new Activity();
		theActivity.setType(type);
	}
}
