package cvapp;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	/*
	 * Validation : Only characters, at least 2 chars, start with a capital letter. Regex ? (A-Z){1}[a-z]+
	 * UI : p:inputText
	 * Mandatory : Yes
	 */
	@Column(name = "lastname")
	@NotNull @Size(min = 2)
	private String lastname;
	
	/*
	 * Validation : Only characters, at least 2 chars, start with a capital letter. Regex ? [A-Z][a-z]+
	 * UI : p:inputText
	 * Mandatory : Yes
	 */
	@Column(name = "firstname")
	@NotNull @Size(min = 2)
	private String firstname;
	
	/*
	 * Validation : regex ? ^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$
	 * UI : p:inputText
	 * Mandatory : Yes
	 */
	@Column(name = "email", unique = true)
	@NotNull @Size(min = 5) //5 : _@_._
	private String email;
	
	/*
	 * Validation : Yes but don't know what to use, regex ?
	 * UI : p:inputText
	 * Mandatory : No
	 */
	@Column(name = "websiteUrl")
	private String websiteUrl;
	
	/*
	 * Validation : None
	 * UI : Primefaces.Calendar
	 * Mandatory : Yes
	 */
	//@Column(name = "birthday")
	//@Temporal(TemporalType.DATE)
	//private Date birthday;
	
	/*
	 * Validation : None ? or maybe add min chars
	 * UI : Primefaces.Password (Feedback)
	 * Mandatory : Yes
	 * Other : This should be the hash of the password, not the password
	 */
	@Column(name = "password")
	@NotNull
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
	private Set<Activity> activities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	/*public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}*/

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", email=" + email
				+ ", websiteUrl=" + websiteUrl + ", password=" + password + ", activities="
				+ activities + "]";
	}
	
	
}
