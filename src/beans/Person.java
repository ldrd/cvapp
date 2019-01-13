package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "picture")
	private String picture;
	
	@Column(name = "lastname")
	@NotNull @Size(min = 2, max = 50)
	private String lastname;
	
	@Column(name = "firstname")
	@NotNull @Size(min = 2, max = 50)
	private String firstname;
	
	@Column(name = "headline")
	@Size(max = 123)
	private String headline;
	
	@Column(name = "position")
	@Size(max = 100)
	private String position;
	
	@Column(name = "email", unique = true)
	@NotNull @Size(min = 5) //5 : _@_._
	private String email;
	
	@Column(name= "address")
	private String address;
	
	@Column(name = "websiteUrl")
	private String websiteUrl;
	
	@Column(name = "summary")
	@Size(max = 700) 
	private String summary;
	
	@Column(name = "birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Column(name = "password")
	@NotNull
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "person")
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("Set pwd " + password);
		this.password = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
		System.out.println("Set pwd2 " + this.password);
	}
	
	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void addActivity(Activity a) {
		activities.add(a);
	}
}
