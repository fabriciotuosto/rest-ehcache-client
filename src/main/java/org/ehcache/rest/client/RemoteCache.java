package org.ehcache.rest.client;

import java.io.Serializable;

import org.ehcache.rest.client.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cache implementation which communicates with a remote cache instance based on
 * EhCache server.
 * 
 * TODO - implement the asynchronous mode of operation
 */
class RemoteCache implements Cache {

	/**
	 * Logger for the class.
	 */
	private static final Logger log = LoggerFactory
			.getLogger(RemoteCache.class);

	/**
	 * Utility used to hash object keys to Strings suitable for use as keys in
	 * the remote cache.
	 */
	private final ObjectHasher hasher;

	/**
	 * 
	 */
	private final RestClient restClient;
	
	/**
	 * The URL where the EhCache server is installed.
	 */
	private String baseUrl;



	/**
	 * 
	 * @param cacheLocation
	 * @param restClient
	 * @param hasher
	 */
	public RemoteCache(CacheLocation cacheLocation, RestClient restClient,
			ObjectHasher hasher) {
		this.restClient = Preconditions.checkNotNull(restClient);
		this.baseUrl = Preconditions.checkNotNull(cacheLocation)
				.getBaseCacheUrl();
		this.hasher = Preconditions.checkNotNull(hasher);
	}

	/**
	 * Get the object with the specified key from the cache. If no object with
	 * the key exists in the cache then return null. Note that the generic
	 * method signature means that the expected type of the object can be
	 * inferred by the compiler without an additional cast e.g. <code>
     * MyComplexObject mco = cache.get(myKeyS)</code>
	 * . The only caveat with this is that the type cannot be a primitive as
	 * auto-boxing will not work.
	 * 
	 * @param key
	 *            the key used to identify the value in the cache
	 * @param <T>
	 *            the type of the object to be returned from the cache, should
	 *            be inferred by the compiler
	 * @return the matching object from the cache, null if no matching value was
	 *         found
	 * @throws IllegalArgumentException
	 *             if either the key or value is null
	 */
	public Serializable get(Serializable key) {
		Preconditions.checkNotNull(key, "The key must not be null");
		if (log.isInfoEnabled()) {
			log.info("Get request for key: {}", key);
		}
		return restClient.get(getCacheKeyUrl(key));
	}

	private String getCacheKeyUrl(Serializable key) {
		String hashKey = baseUrl + "/" + hasher.hashObject(key);
		log.info(hashKey);
		return hashKey;
	}

	/**
	 * Put the the specified value in the cache at the location specified by the
	 * key. If a value already exists for the key it will be overwritten by the
	 * new value. The put will be performed using the specified InsertionMode
	 * for the cache.
	 * 
	 * @param key
	 *            the serializable key to store the object against
	 * @param value
	 *            the serializable object to be stored
	 * @return true if the value was successfully added to the cache, false
	 *         otherwise. Note that if the insert is to be performed
	 *         asynchronously, there is no way to check whether the insert
	 *         ultimately succeeded and true will always be returned.
	 * @throws IllegalArgumentException
	 *             if either the key or value is null
	 */
	public boolean put(Serializable key, Serializable value) {
		Preconditions.checkNotNulls(key, value);
		if (log.isInfoEnabled()) {
			log.info("Put request for key: [{}], value: [{}] ", new Object[] {
					key, value });
		}
		return restClient.put(getCacheKeyUrl(key), value);
	}

	/**
	 * @see Cache.put
	 */
	public boolean put(Serializable key, Serializable value, Duration duration) {
		boolean result = put(key, value);
		new TimeOutHandler(this, key, value, duration);
		return result;
	}

	/**
	 * Delete the object with the specified key from the cache. If deletion is
	 * successfully achieved then <code>true</code> is returned. If no object
	 * was held in the cache with the key, or there was an error,
	 * <code>false</code> is returned.
	 * 
	 * @param key
	 *            the serializable key used to identify the value in the cache
	 * @return <code>true</code> if an cached object was successfully deleted,
	 *         <code>false</code> otherwise
	 * @throws IllegalArgumentException
	 *             if the key is null
	 */
	public boolean delete(Serializable key) {
		Preconditions.checkNotNull(key, "The key must not be null");
		if (log.isInfoEnabled()) {
			log.info("Delete request for key: [{}]", key);
		}
		return restClient.delete(getCacheKeyUrl(key));
	}

}