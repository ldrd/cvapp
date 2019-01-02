package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
			System.out.println("4 step");
			personController.setThePerson(personController.getPerson(user.getId()));
			System.out.println("5 step");
			return "cv?faces-redirect=true";
		}
		return null;
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
