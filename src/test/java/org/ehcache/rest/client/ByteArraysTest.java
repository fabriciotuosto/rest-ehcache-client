package org.ehcache.rest.client;

import static org.junit.Assert.*;

import org.ehcache.rest.client.ByteArrays;
import org.junit.Before;
import org.junit.Test;


public class ByteArraysTest {

	private ByteArrays transformer; 
	
	@Before
	public void createTransformer(){
		transformer = new ByteArrays();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_exception(){
		transformer.toByteArray(null);
	}
	
	@Test
	public void should_return_object_transformed(){
		String o = "123456789";
		assertNotNull(transformer.toByteArray(o));
	}
	
	@Test
	public void should_return_same_transformation(){
		String o = "123456789";
		byte[] first = transformer.toByteArray(o);
		byte[] second = transformer.toByteArray(o);
		assertArrayEquals(first, second);
	}
	
	
	@Test
	public void should_return_different_transformation(){
		String o = "123456789";
		String o2 = "123459786";
		byte[] first = transformer.toByteArray(o);
		byte[] second = transformer.toByteArray(o2);
		
		assertDifferentArrays(first,second);
	}

	private void assertDifferentArrays(byte[] first, byte[] second) {
		assertNotNull(first);
		assertNotNull(second);
		int shortest = Math.min(first.length, second.length);
		boolean equals = true;
		for (int i = 0; i < shortest; i++) {
			equals = equals && (first[i]==second[i]);
		}
		assertFalse(equals);
	}
}
