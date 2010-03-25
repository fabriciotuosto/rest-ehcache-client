package org.ehcache.rest.client;

import org.ehcache.rest.client.util.Preconditions;

import java.io.Serializable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



/**
 * This class gives support to add objects to cache with an specified time to live
 * it needs references to a cache instance, what key value it is supposed to
 * monitor and which is the time to live of the value into the cache.
 * 
 * It basically guarantees that if the value after the given time is the same that
 * was put it will removed it from cache, and left the cache intact if the value
 * has changed during the sleeping time
 *
 */
class TimeOutHandler {

	/**
	 * provides support for executing delayed operations
	 * it remains in a static field because it creates threads
	 * and they are expensive to be continuously creating them
	 */
    private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

	/**
	 * 
	 * @param cache the cache that is going to be monitored
	 * @param key   the key to monitor in the given cache
	 * @param value the value that is being retained for the duration
	 * @param duration the time the value will be held on cache
	 */
	public TimeOutHandler(Cache cache, Serializable key, Serializable value, Duration duration) {
		Preconditions.checkNotNulls(cache,key,value,duration);
		Runnable checkCacheTask = () -> {
			Serializable retrievedObject = cache.get(key);
			if (value.equals(retrievedObject)) {
				cache.delete(key);
			}
		};
		executor.schedule(checkCacheTask, duration.inMiliseconds(), TimeUnit.MILLISECONDS);
	}


}
