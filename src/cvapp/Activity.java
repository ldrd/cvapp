package cvapp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue
	private Long id;
	
	/*
	 * Validation : birthday < year < now
	 * UI : Primefaces.InputNumber
	 * Mandatory : Yes
	 */
	@Column(length = 4)
	@NotNull
	private int year;
	
	/*
	 * Validation : None
	 * UI : Primefaces.SelectOneMenu
	 * Mandatory : Yes
	 */
	@Column
	@NotNull
	private String type;
	
	/*
	 * Validation : None
	 * UI : p:inputText
	 * Mandatory : Yes
	 */
	@Column
	@NotNull @Size(min = 3, max = 100)
	private String title;
	
	/*
	 * Validation : None
	 * UI : Primefaces.InputTextarea (Autoresize)
	 * Mandatory : No
	 */
	@Lob
	@Size(min = 5, max = 4000)
	private String description;
	
	/*
	 * Validation : Yes but don't know what to use, regex ?
	 * UI : p:inputText
	 * Mandatory : No
	 */
	@Column
	private String websiteUrl;
	
	@ManyToOne
	@JoinColumn(name = "person", nullable = false)
	private Person person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
