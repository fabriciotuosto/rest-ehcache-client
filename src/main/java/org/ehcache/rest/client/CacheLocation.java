package org.ehcache.rest.client;

import org.ehcache.rest.client.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple object which will construct the url for a cache instance.
 */
public class CacheLocation {

    /**
     * Logger for the class.
     */
    private static final Logger log = LoggerFactory.getLogger(CacheLocation.class);

    /**
     * The url for the cache service.
     */
    private final String baseCacheUrl;

    /**
     * Construct a url for the cache service.
     *
     * @param host the host on which the cache is located
     * @param port the port number the cache service is running on
     * @param path the path of the cache service
     * @param cacheName the name of the cache to use;
     */
    public CacheLocation(String host,int port,String path,String cacheName) {
    	Preconditions.checkNotNulls(host,path,cacheName);
        this.baseCacheUrl = "http://"+host+":"+port+"/"+path+"/"+cacheName;
        if (log.isInfoEnabled()){
        	log.info("Creating url: [{}] for cache on host [{}], port [{}] with path [{}]",
        			new Object[]{baseCacheUrl, host, Integer.valueOf(port), path});
        }
    }

    /**
     * Get the base url for this cache server. Actual cache instances will be extensions
     * to this url.
     *
     * @return the base url
     */
    public String getBaseCacheUrl() {
        return baseCacheUrl;
    }

}
