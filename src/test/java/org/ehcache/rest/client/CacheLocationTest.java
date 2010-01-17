package org.ehcache.rest.client;

import org.ehcache.rest.client.CacheLocation;
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

        CacheLocation location = new CacheLocation("localhost", 8001, "cache-impl","dpfsm");
        assertEquals("Url is not correct",
                "http://localhost:8001/cache-impl/dpfsm",
                location.getBaseCacheUrl());
    }
}
