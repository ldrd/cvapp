package beans;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Activity")
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/*
	 * Validation : birthday < year < now
	 * UI : Primefaces.InputNumber
	 * Mandatory : Yes
	 */
	@Basic(optional = false)
	private int year;
	
	/*
	 * Validation : None
	 * UI : Primefaces.SelectOneMenu
	 * Mandatory : Yes
	 */
	@Basic(optional = false)
	private String type;
	
	/*
	 * Validation : None
	 * UI : p:inputText
	 * Mandatory : Yes
	 */
	@Basic(optional = false)
	private String title;
	
	/*
	 * Validation : None
	 * UI : Primefaces.InputTextarea (Autoresize)
	 * Mandatory : No
	 */
	@Basic
	private String description;
	
	/*
	 * Validation : Yes but don't know what to use, regex ?
	 * UI : p:inputText
	 * Mandatory : No
	 */
	@Basic
	private String websiteUrl;
	
	@ManyToOne
	@JoinColumn(name = "person", nullable = false)
	private Person person;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
