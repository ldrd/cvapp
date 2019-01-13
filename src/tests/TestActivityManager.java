package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Activity;
import beans.Person;
import dao.ActivityManager;
import dao.PersonManager;

public class TestActivityManager {

	@EJB
    PersonManager personMgr;
	
	@EJB
    ActivityManager activityMgr;
	
	@Before
    public void setUp() throws Exception {
        EJBContainer.createEJBContainer().getContext().bind("inject", this);
    }

    @After
    public void tearDown() throws Exception {
        EJBContainer.createEJBContainer().close();
    }
    
    @Test
    public void testFindByName() {
    	Person p = new Person();
    	if (personMgr.findAll().size() > 0) {
    		p = personMgr.findAll().get(0);
    	}
    	else {
	    	p.setEmail("tactivity@t.t");
	    	p.setFirstname("testF");
	    	p.setLastname("testL");
	    	p.setPassword("test");
	    	personMgr.save(p);
    	}
    	Activity a = new Activity();
    	a.setYearFrom(2017);
    	a.setYearTo(2018);
    	a.setTitle("Test activity");
    	a.setPerson(p);
    	a.setType("experience");
    	a = activityMgr.save(a);
    	assertEquals(1, activityMgr.findActivitiesByName("test", 0, 10).size());
    	
    }
}
