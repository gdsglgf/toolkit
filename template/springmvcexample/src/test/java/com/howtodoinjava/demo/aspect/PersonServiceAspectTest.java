package com.howtodoinjava.demo.aspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test.xml")
public class PersonServiceAspectTest {
	@Test
	public void test() {
		String personName = "Jim";
    	personService.addPerson(personName);
    	System.out.println("-----------------------");
    	personService.deletePerson(personName);
    	System.out.println("-----------------------");
    	personService.editPerson(personName);
	}
	
	@Autowired
	PersonService personService;
}
