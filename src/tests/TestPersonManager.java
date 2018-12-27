package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cvapp.Person;
import cvapp.PersonManager;

public class TestPersonManager {
	
    static EJBContainer container;
    static PersonManager mgr;
    
    @BeforeClass
    static public void setUp() throws Exception {
    	container = EJBContainer.createEJBContainer();
        final String name = "java:global/cvapp/PersonManager";
        mgr = (PersonManager) container.getContext().lookup(name);
        //EJBContainer.createEJBContainer().getContext().bind("inject", this);
    }

    @AfterClass
    static public void tearDown() throws Exception {
    	container.close();
    }
    
    @Before
    public void before() throws Exception {
            container.getContext().bind("inject", this);
    }

    @After
    public void after() throws Exception {
            container.getContext().unbind("inject");
    }

    @Inject
    Person p;

    @Test
    public void testFindPersons() {
        assertNotNull(mgr.findAll());
    }
    
    @Test
    public void testAdd() {
    	p.setFirstname("testFirstName");
    	p.setLastname("testLastName");
    	p.setEmail("test@test.test");
    	p.setPassword("testPwd");
    	int count = mgr.findAll().size();
    	p = mgr.save(p);
    	assertEquals(mgr.findAll().size(), count + 1);
    }
    
    @Test
    public void testUpdate() {
    	
    }
    
    @Test
    public void testDelete() {
    	
    }
    
    @Test
    public void testFindByName1() {
    	
    }
    
    @Test
    public void testFindByName2() {
    	
    }
    
    @Test
    public void testFindByName3() {
    	
    }

}
