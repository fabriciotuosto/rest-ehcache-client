package org.ehcache.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests that the RemoteCache connects to the EhCache server and performs
 * the correct operations.
 *
 * At the moment the tests assume a running (vanilla) instance of EhCache.
 * This should be enhanced by running EhCache on an embedded Jetty server
 * started in the setup of this class.
 */

public class RemoteCacheTest {

    private Cache cache;
    private static CacheURI location;
    private Md5HashGenerator hasher = new Md5HashGenerator(new ByteArrays());
	private RestClient restClient;

    @BeforeClass
    public static void createLocation(){
    	location = new CacheURI("localhost", 80, "ehcache/rest", "name");    	
    }
	
	@Before
    public void setup() {
        restClient = mock(RestClient.class);    
        cache = new RemoteCache(location,restClient,hasher);
    }  

    @Test
    public void testGetOnMissingValue() {
    	when(restClient.get(any(String.class))).thenReturn(null);
        assertNull("Empty cache contains a value", cache.get("not-there"));
    }

    @Test
    public void testPutStringIsRetrievable() {
    	when(restClient.put(any(String.class),eq("test-value"))).thenReturn(true);
    	when(restClient.get(any(String.class))).thenReturn("test-value");
    	cache.put("test-key1", "test-value");
        assertEquals("Value not retrieved from cache", "test-value", cache.get("test-key1"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetNullKeyThrowsException() {
        cache.get(null);
        fail();
    }

    @Test(expected=IllegalArgumentException.class) 
    public void testPutNullKeyThrowsException() {
        cache.put(null, 1234);
        fail();
    } 

    @Test(expected=IllegalArgumentException.class)
    public void testPutNullValueThrowsException() {
        cache.put(1234, null);
        fail();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDeletionNullKeyThrowsException() {
        cache.delete(null);
        fail();
    }

    @Test
    public void testDeletionOfNonExistentItem() {
    	when(restClient.delete(any(String.class))).thenReturn(false);
        assertFalse("Deletion of missing item should be false", cache.delete("missing-key"));
    }
}