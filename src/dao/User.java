package dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import beans.Person;

@Stateful
public class User {
        
	private Long id;
    private String email;
    private String password;
    private boolean loggedIn;
    
    @EJB
	PersonManager mgr;

    public boolean login() {
        List<Person> persons = mgr.findPersonsByEmail(email);
        //Should be = 1
        System.out.println("firstStep");
        if (persons.size() != 1) return false;
        
        Person potentialUser = persons.get(0);
        System.out.println("secondStep");
        if (!potentialUser.getPassword().equals(password)) return false;
        
        id = potentialUser.getId();
        loggedIn = true;
        System.out.println("thirdStep");
        return true;
    }

    public void logout() {
        id = null;
        email = password = "";
        setLoggedIn(false);
    }

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLogin() {
		return email;
	}

	public void setLogin(String login) {
		this.email = login;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}