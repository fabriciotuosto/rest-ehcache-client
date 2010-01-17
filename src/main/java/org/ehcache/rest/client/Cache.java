package org.ehcache.rest.client;

import java.io.Serializable;

/**
 * Standard cache interface, representing all the operations that can be
 * performed on a standard cache i.e. get, put and delete. 
 */
public interface Cache {

    /**
     * Get the object with the specified key from the cache. If no object with
     * the key exists in the cache then return null. Note that the generic
     * method signature means that the expected type of the object can be
     * inferred by the compiler without an additional cast e.g.
     * <code>
     * MyComplexObject mco = cache.get(myKey)</code>.
     *
     * @param key the serializable key used to identify the value in the cache
     * @return the matching object from the cache, null if no matching value was found
     * @throws IllegalArgumentException if the key is null
     */
    public Serializable get(Serializable key);

    /**
     * Put the the specified value in the cache at the location specified by the
     * key. If a value already exists for the key it will be overwritten by the
     * new value. The put will be performed using the default InsertionMode for
     * the cache.
     *
     * @param key the serializable key to store the object against
     * @param value the serializable object to be stored
     * @return true if the value was successfully added to the cache, false otherwise.
     * Note that if the insert is to be performed asynchronously, there is no way
     * to check whether the insert ultimately succeeded and true will always be returned.
     * @throws IllegalArgumentException if either the key or value is null
     */
    public boolean put(Serializable key, Serializable value);


    /**
     * Put the the specified value in the cache at the location specified by the
     * key. If a value already exists for the key it will be overwritten by the
     * new value. The put will be performed using the specified InsertionMode for
     * the cache. The value will be remain in the Cache for the duration specified
     * as argument, unless the value is overwriting by another operation
     *
     * @param key the Serializable key to store the object against
     * @param value the Serializable object to be stored
     * @param duration the time the value will be placed on Cache
     * @return true if the value was successfully added to the cache, false otherwise.
     * Note that if the insert is to be performed asynchronously, there is no way
     * to check whether the insert ultimately succeeded and true will always be returned.
     * @throws IllegalArgumentException if either the key or value is null
     */
    public boolean put(Serializable key, Serializable value, Duration duration);    
    
    /**
     * Delete the object with the specified key from the cache. If deletion is
     * successfully achieved then <code>true</code> is returned. If no object
     * was held in the cache with the key, or there was an error, <code>false</code>
     * is returned.
     *
     * @param key the serializable key used to identify the value in the cache
     * @return <code>true</code> if an cached object was successfully deleted,
       <code>false</code> otherwise
     * @throws IllegalArgumentException if the key is null
     */
    public boolean delete(Serializable key);


}
