package controllers;

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
}
