package org.ehcache.rest.client;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@Ignore
public class RemoteCacheIntegrationTest {

	private static Cache cache;

	@Test
	public void testGetOnMissingValue() {
		assertNull("Empty cache contains a value", cache.get("not-there"));
	}

	@Test
	public void testPutStringIsRetrievable() {

		cache.put("test-key1", "test-value");
		assertEquals("Value not retrieved from cache", "test-value", cache
				.get("test-key1"));
	}

	@Test
	public void testPutObjectIsRetrievable() {

		// Need to keep as ArrayList as List is not serializable
		ArrayList<String> list = new ArrayList<String>();
		list.add("Entry one");
		list.add("Entry two");

		cache.put("test-key2", list);
		assertEquals("Value not retrieved from cache", list, cache
				.get("test-key2"));
	}

	@Test
	public void testObjectKeySucceeds() {

		ArrayList<String> myKey1 = new ArrayList<String>();
		myKey1.add("SomeString");
		ArrayList<String> myKey2 = new ArrayList<String>();
		myKey2.add("SomeString");

		cache.put(myKey1, 1234);

		assertEquals("Value not retrieved from cache", 1234, cache.get(myKey2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetNullKeyThrowsException() {
		cache.get(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPutNullKeyThrowsException() {
		cache.put(null, 1234);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPutNullValueThrowsException() {
		cache.put(1234, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeletionNullKeyThrowsException() {
		cache.delete(null);
	}

	@Test
	public void testDeletionOfNonExistentItem() {
		assertFalse("Deletion of missing item should be false", cache
				.delete("missing-key"));
	}

	@Test
	public void testDeletionWorksCorrectly() {
		cache.put(1234, 5678);

		assertEquals("Item not retrieved from cache", 5678, cache.get(1234));
		assertTrue("Deleting item from cache should succeed", cache
				.delete(1234));
		assertEquals("Item should not be in cache", null, cache.get(1234));
	}

	@Test
	public void testMultipleValuesAreRetrievable() {
		cache.put(1, "First");
		cache.put(2, "Another");

		assertEquals("First item is not retrievable", "First", cache.get(1));
		assertEquals("Second item is not retrievable", "Another", cache.get(2));
		assertEquals("First item is not retrievable", "First", cache.get(1));
	}

	@Test
	public void should_expire_value_from_cache() throws InterruptedException {
		Duration time = new Duration(1L, TimeUnit.MILLISECONDS);
		cache.put(1, "First", time);
		Thread.sleep(100L);
		assertNull("Item Should Be Removed", cache.get(1));
	}
}
