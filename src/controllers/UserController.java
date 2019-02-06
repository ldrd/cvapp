package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import beans.Person;

@ManagedBean(name = "user")
@SessionScoped
@Stateful
public class UserController {
	
	private Person user = new Person();
	private boolean loggedIn;

	@ManagedProperty(value="#{person}")
	private PersonController personController;
	
	public String login() {
		if (loggedIn) return null;
		
		Person person = personController.findByEmail(user.getEmail());
        if (person == null) return null;
        if (!person.getPassword().equals(user.getPassword())) return null;
        
        loggedIn = true;
        user = person;

        personController.setThePerson(user);
		return "cv?faces-redirect=true";
	}
	
	public String signup() {
		personController.savePerson(user);
		return login();
	}

	public String logout() {
		user = new Person();
		loggedIn = false;
		return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
		
	}
	
	public void setPersonController(PersonController pc) {
		this.personController = pc;
	}
	
	public String home() {
		if (loggedIn) {
			personController.setThePerson(user);
			return "cv?faces-redirect=true";
		}
		user = new Person();
		return "inscription?faces-redirect=true";	
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
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public Person getUser() {
		return user;
	}

	public void setUser(Person user) {
		this.user = user;
	}
}
