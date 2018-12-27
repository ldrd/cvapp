package cvapp;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "person")
@SessionScoped
public class PersonController {
	
	@EJB
	PersonManager mgr;
	
	@PostConstruct
    public void init() {
        System.out.println("Create " + this);
        if (mgr.findAll().size() == 0) {
            Person p1 = new Person();
            p1.setFirstname("Loic");
            p1.setLastname("Drouard");
//            String pattern = "dd-mm-yyyy";
//            SimpleDateFormat format = new SimpleDateFormat(pattern);
//            
//            try {
//				p1.setBirthday(format.parse("29-03-1995"));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
            p1.setEmail("loic.drouard@free.fr");
            p1.setPassword("llllllllllllll");
            mgr.save(p1);
        }
    }
	
	public Person savePerson(Person person) {
		return mgr.save(person);
	}
	
	public Person getPerson(Long id) {
		return mgr.find(id);
	}
	
	public List<Person> findByName(String name) {
		return mgr.findPersonsByName(name);
	}
	
	public List<Person> getPersons() {
		return mgr.findAll();
	}
}
