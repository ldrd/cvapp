package cvapp;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "activity")
@SessionScoped
public class ActivityController {

	@EJB
	ActivityManager mgr;
	
	public Activity saveActivity(Activity activity) {
		return mgr.save(activity);
	}
	
	public Activity getActivity(Long id) {
		return mgr.find(id);
	}
	
	public void deleteActivity(Activity activity) {
		mgr.delete(activity);
	}
	
	public List<Activity> findActivitiesByPerson(Long idPerson) {
		return mgr.findActivitiesByPerson(idPerson);
	}
}
