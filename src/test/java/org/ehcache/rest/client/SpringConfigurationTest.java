package org.ehcache.rest.client;

import junit.framework.TestCase;

import org.ehcache.rest.client.Cache;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringConfigurationTest extends TestCase {

	private ClassPathXmlApplicationContext context;

	protected void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[] {"cache-service.xml"});
		super.setUp();
	}
	
	
	public void testSpringCreation() throws Exception {
		Cache location = (Cache) context.getBean("cache");
		
		assertNotNull(location);
	}

}
