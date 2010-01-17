package org.ehcache.rest.client;

import java.util.concurrent.TimeUnit;

import org.ehcache.rest.client.Cache;
import org.ehcache.rest.client.Duration;
import org.ehcache.rest.client.TimeOutHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class TimeOutHandlerTest {

	private Cache cache;
	private Duration time;
	private static final String value = "key";
	private static final String key = "value";
	private TimeOutHandler handler;

	@Before
	public void prepareMocks(){
		cache = mock(Cache.class);
		time = new Duration(10l,TimeUnit.MILLISECONDS);
	}
	
	@Test
	public void should_create_time_out_handler(){
		handler = new TimeOutHandler(cache,key,value,time);
		assertNotNull(handler);
	}
	
	@Test
	public void should_check_after_given_time_remains_cache() throws InterruptedException{
		when(cache.get(key)).thenReturn(value);
		handler = new TimeOutHandler(cache,key,value,time);
		Thread.sleep(50);
		verify(cache).get(key);
	}
	
	
	@Test
	public void should_remove_after_given_time_remains_cache() throws InterruptedException{
		when(cache.get(key)).thenReturn(value);
		handler = new TimeOutHandler(cache,key,value,time);
		Thread.sleep(50);
		verify(cache).get(key);
		verify(cache).delete(key);
	}	
	
}
