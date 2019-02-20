package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Person;
import dao.PersonManager;

public class TestPersonManager {
	
	@EJB
    PersonManager mgr;
    
    @Before
    public void setUp() throws Exception {
        EJBContainer.createEJBContainer().getContext().bind("inject", this);
    }

    @After
    public void tearDown() throws Exception {
        EJBContainer.createEJBContainer().close();
    }

    @Test
    public void testFindAllPersons() {
        assertNotNull(mgr.findAll());
    }
    
    @Test
    public void testAddPerson() {
    	int count = mgr.findAll().size();
    	Person p = new Person();
    	p.setEmail("tadd@t.t");
    	p.setFirstname("testF");
    	p.setLastname("testL");
    	p.setPassword("test123");
    	mgr.save(p);
    	assertEquals(count + 1, mgr.findAll().size());
    }
    
    @Test
    public void testUpdatePerson() {
    	Person p = new Person();
    	p.setEmail("tupdate@t.t");
    	p.setFirstname("testF");
    	p.setLastname("testL");
    	p.setPassword("test123");
    	p = mgr.save(p);
    	p.setFirstname("testFirst");
    	p = mgr.save(p);
    	assertEquals("testFirst", mgr.findPersonByEmail("tupdate@t.t").getFirstname());
    }
    
    @Test
    public void testDeletePerson() {
    	int count = mgr.findAll().size();
    	Person p = new Person();
    	p.setEmail("tdelete@t.t");
    	p.setFirstname("testF");
    	p.setLastname("testL");
    	p.setPassword("test123");
    	p = mgr.save(p);
    	mgr.delete(p);
    	assertEquals(count, mgr.findAll().size());
    }
    
    public void testFindPersonByEmail() {
    	Person p = new Person();
    	p.setEmail("temail@t.t");
    	p.setFirstname("testF");
    	p.setLastname("testL");
    	p.setPassword("test123");
    	p = mgr.save(p);
    	assertEquals(p.getId(), mgr.findPersonByEmail("temail@t.t").getId());
    }
    
    public void testFindPersonByName() {
    	Person p1 = new Person();
    	p1.setEmail("tname1@t.t");
    	p1.setFirstname("xyz");
    	p1.setLastname("testL");
    	p1.setPassword("test123");
    	p1 = mgr.save(p1);
    	Person p2 = new Person();
    	p2.setEmail("tname1@t.t");
    	p2.setFirstname("xyy");
    	p2.setLastname("testL");
    	p2.setPassword("test123");
    	p2 = mgr.save(p2);
    	assertEquals(2, mgr.findPersonsByName("xy").size());
    }
}
