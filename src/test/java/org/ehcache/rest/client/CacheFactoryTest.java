package org.ehcache.rest.client;

import org.ehcache.rest.client.CacheFactory;
import org.junit.Assert;
import org.junit.Test;
public class CacheFactoryTest {

	
	@Test
	public void testCreateCache(){
		CacheFactory factory = new CacheFactory();
		Assert.assertNotNull(factory.getCache());
	}
	
}
