package org.ehcache.rest.client;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.ehcache.rest.client.Cache;
import org.ehcache.rest.client.CacheLocation;
import org.ehcache.rest.client.Md5ObjectHasher;
import org.ehcache.rest.client.ByteArrays;
import org.ehcache.rest.client.RemoteCache;
import org.ehcache.rest.client.RestClient;
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
    private static CacheLocation location;
    private Md5ObjectHasher hasher = new Md5ObjectHasher(new ByteArrays());
	private RestClient restTemplate;

    @BeforeClass
    public static void createLocation(){
    	location = new CacheLocation("localhost", 80, "rest/dpfsm", "name");    	
    }
	
	@Before
    public void setup() {
        restTemplate = mock(RestClient.class);    
        cache = new RemoteCache(location,restTemplate,hasher);
    }  

    @Test
    public void testGetOnMissingValue() {
//    	when(response.isSuccessful()).thenReturn(false);
//    	when(response.readObject()).thenReturn(null);
//        assertNull("Empty cache contains a value", cache.get("not-there"));
    }

    @Test
    public void testPutStringIsRetrievable() {
//    	when(executor.execute(any(PutMethod.class))).thenReturn(response);
//    	when(response.isSuccessful()).thenReturn(false);
//    	
//    	HttpResponseInterpreter response2 = mock(HttpResponseInterpreter.class);
//    	when(executor.execute(any(GetMethod.class))).thenReturn(response2);
//    	when(response2.readObject()).thenReturn("test-value");
//    	
//    	cache.put("test-key1", "test-value");
//        assertEquals("Value not retrieved from cache", "test-value", cache.get("test-key1"));
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
//    	when(executor.execute(any(DeleteMethod.class))).thenReturn(response);
//    	when(response.isSuccessful()).thenReturn(false);
//    	
//        assertFalse("Deletion of missing item should be false", cache.delete("missing-key"));
    }
}


