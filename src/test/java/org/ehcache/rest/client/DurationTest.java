package org.ehcache.rest.client;


import java.util.concurrent.TimeUnit;

import org.ehcache.rest.client.Duration;

import junit.framework.TestCase;

public class DurationTest extends TestCase{

	
	public void testShould_return_valid_duration(){
		Duration duration = new Duration(10L,TimeUnit.MILLISECONDS);
		assertEquals(10L,duration.inMiliseconds());
	}
	
	public void testShould_return_valid_duration_of_seconds(){
		Duration duration = new Duration(10L,TimeUnit.SECONDS);
		assertEquals(10*1000L,duration.inMiliseconds());
	}
	
	public void testShould_return_valid_duration_of_minutes(){
		Duration duration = new Duration(10L,TimeUnit.MINUTES);
		assertEquals(10*1000*60L,duration.inMiliseconds());
	}
	
	public void testShould_return_valid_duration_of_hours(){
		Duration duration = new Duration(10L,TimeUnit.HOURS);
		assertEquals(10*1000*60*60L,duration.inMiliseconds());
	}
	
	public void testShould_return_valid_duration_of_microseconds(){
		Duration duration = new Duration(1000L,TimeUnit.MICROSECONDS);
		assertEquals(1L,duration.inMiliseconds());
	}
		
	public void testShould_return_valid_duration_of_nanoseconds(){
		Duration duration = new Duration(1000000L,TimeUnit.NANOSECONDS);
		assertEquals(1L,duration.inMiliseconds());
	}	
	
	public void testShould_return_valid_duration_of_days(){
		Duration duration = new Duration(1L,TimeUnit.DAYS);
		assertEquals(1000*60*60*24L,duration.inMiliseconds());
	}	
	
}
