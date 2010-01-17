package org.ehcache.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


/**
 * Tests that the Md5ObjectHasher hashes values in a consistent manner.
 */
public abstract class HashGeneratorContractTest {

	protected abstract HashGenerator getHasher();	
	
    @Test
    public void testStringHash() {

        String hash = getHasher().generateHash("Hash me");
        assertNotNull("Hash must not be null", hash);
    }

    @Test
    public void testObjectHash() {

        String hash = getHasher().generateHash(new ArrayList<Object>());
        assertNotNull("Hash must not be null", hash);
    }

    @Test
    public void testObjectHashDifferent() {

        ArrayList<Object> obj1 = new ArrayList<Object>();
        obj1.add("A");

        ArrayList<Object> obj2 = new ArrayList<Object>();
        obj2.add("B");

        assertTrue("Hashes must not be the same",
                !getHasher().generateHash(obj1).equals(getHasher().generateHash(obj2)));
    }

    @Test
    public void testObjectHashConsistent() {

        ArrayList<Object> obj1 = new ArrayList<Object>();
        obj1.add("A");

        ArrayList<Object> obj2 = new ArrayList<Object>();
        obj2.add("A");

        assertEquals("Hashes should be the same",
        		getHasher().generateHash(obj1), getHasher().generateHash(obj2));
    }

    @Test
    public void testHashHasValidChars() {

        validateChars(getHasher().generateHash("Hash me"));
        validateChars(getHasher().generateHash("and me too"));
        validateChars(getHasher().generateHash(new ArrayList<String>()));
        validateChars(getHasher().generateHash(new Object[]{}));
    }

    private void validateChars(String hash) {

        Set<Character> valid = new HashSet<Character>(
                Arrays.asList('1', '2', '3','4','5','6','7','8','9','0','a','b','c','d','e','f'));

        for (char c : hash.toCharArray()) {

            assertTrue("Invalid character in hash: [" + c + "]", valid.contains(c));
        }
    }
}
