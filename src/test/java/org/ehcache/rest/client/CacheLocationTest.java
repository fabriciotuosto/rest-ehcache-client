package org.ehcache.rest.client;

import org.ehcache.rest.client.CacheURI;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Test that the CacheLocation class constructs URLs correctly.
 *
 * TODO - extend with tests for malformed urls, extra slashes etc.
 */
public class CacheLocationTest {

    @Test
    public void testUrlConstructedCorrectly() {

        CacheURI location = new CacheURI("localhost", 8001, "cache-impl","test");
        assertEquals("Url is not correct",
                "http://localhost:8001/cache-impl/test",
                location.getBaseCacheUrl());
    }
}
