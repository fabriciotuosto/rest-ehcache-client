package org.ehcache.rest.client;

import java.io.Serializable;

/**
 * Hashes an object into a String such that it can be used as the key
 * within a REST-based URI. The String key returned needs to be unique
 * across different objects (or as unique as possible), whilst the
 * same object must ALWAYS hash to the same value to allow retrieval
 * of objects by the key. The primary implementation will hash as a long
 * String based on an algorithm (e.g. MD5), however other implementations
 * may want to hash as a simpler result to enable debugging within the
 * cache.
 */
interface HashGenerator {

    /**
     * Hash the passed object, producing a String which can be used as
     * a key. The key produced will be unique (as far as possible) across
     * different input objects. On the other hand, hashing 2 equal objects
     * will always produce the same key.
     *
     * @param object the object to be hashed
     * @return the hashed version of the object
     */
    public String generateHash(Serializable object);
}
