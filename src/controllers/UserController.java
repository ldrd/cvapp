package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import beans.Person;
import dao.User;

@ManagedBean(name = "user")
@SessionScoped
public class UserController {
	
	@EJB
	private User user;
	
	@ManagedProperty(value="#{person}")
	private PersonController personController;
	
	public String login() {
		if (user.login()) {
			personController.setThePerson(personController.getPerson(user.getId()));
			return "cv?faces-redirect=true";
		}
		return null;
	}
	
	public String signup() {
		Person p = new Person();
		p.setFirstname(user.getFirstname());
		p.setLastname(user.getLastname());
		p.setEmail(user.getLogin());
		p.setPassword(user.getPassword());
		personController.savePerson(p);
		return login();
	}

	public String logout() {
		user.logout();
		return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
		
	}
	
	public User getUser() {
		return user;
	}
	
	public void setPersonController(PersonController pc) {
		this.personController = pc;
	}
	
	public String home() {
		if (user.isLoggedIn()) {
			personController.setThePerson(personController.getPerson(user.getId()));
			return "cv?faces-redirect=true";
		}
		return "inscription?faces-redirect=true";	
	}
	
	public String searchPersons() {
		personController.findByName();
		return "searchresult?faces-redirect=true";
	}
	
	public List<String> getMonths() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		Map<String, Integer> monthsMap = Calendar.getInstance().getDisplayNames(
																Calendar.MONTH,
																Calendar.LONG,
																locale);
		List<String> months = new ArrayList<>();
		Map<Integer, String> invertedMap = monthsMap.entrySet().stream()
	              								.collect(Collectors.toMap(Entry::getValue, Entry::getKey));
		for (int i = 0; i < 12; i++)
			months.add(invertedMap.get(i));
		return months;
	}
}
