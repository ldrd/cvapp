package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Person")
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/*
	 * Validation : Only characters, at least 2 chars, start with a capital letter. Regex ? (A-Z){1}[a-z]+
	 * UI : p:inputText
	 * Mandatory : Yes
	 */
	@Basic(optional = false)
	private String lastname;
	
	/*
	 * Validation : Only characters, at least 2 chars, start with a capital letter. Regex ? [A-Z][a-z]+
	 * UI : p:inputText
	 * Mandatory : Yes
	 */
	@Basic(optional = false)
	private String firstname;
	
	/*
	 * Validation : regex ? ^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$
	 * UI : p:inputText
	 * Mandatory : Yes
	 */
	@Basic(optional = false)
	@Column(unique = true)
	private String email;
	
	/*
	 * Validation : Yes but don't know what to use, regex ?
	 * UI : p:inputText
	 * Mandatory : No
	 */
	@Basic
	private String websiteUrl;
	
	/*
	 * Validation : None
	 * UI : Primefaces.Calendar
	 * Mandatory : Yes
	 */
	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	/*
	 * Validation : None ? or maybe add min chars
	 * UI : Primefaces.Password (Feedback)
	 * Mandatory : Yes
	 * Other : This should be the hash of the password, not the password ?
	 */
	@Basic(optional = false)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private Set<Activity> activities;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
