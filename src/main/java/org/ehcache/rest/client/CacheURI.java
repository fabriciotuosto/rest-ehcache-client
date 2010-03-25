package org.ehcache.rest.client;

import org.ehcache.rest.client.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;


/**
 * Simple object which will construct the url for a cache instance.
 */
public class CacheURI {

    /**
     * Logger for the class.
     */
    private static final Logger log = LoggerFactory.getLogger(CacheURI.class);

    private static String URL_FORMAT = "http://%s:%s/%s/%s";
    /**
     * The url for the cache service.
     */
    private final URI baseCacheUrl;

    /**
     * Construct a uri for the cache service.
     *
     * @param host      the host on which the cache is located
     * @param port      the port number the cache service is running on
     * @param path      the path of the cache service
     * @param cacheName the name of the cache to use;
     */
    public CacheURI(String host, int port, String path, String cacheName) {
        this(String.format(URL_FORMAT, host, port, path, cacheName));
        Preconditions.checkNotNulls(host, path, cacheName);
    }


    /**
     * Constructs a URI location of the cache the url must be a rest uri based on http
     * should look like "http://host:port/ehcache/rest/cacheName
     *
     * @param url
     */
    public CacheURI(String url) {
        this(URI.create(url));
    }

    /**
     * Constructs a URI location of the cache the url must be a rest uri based on http
     * should look like "http://host:port/ehcache/rest/cacheName
     */
    public CacheURI(URI uri) {
        this.baseCacheUrl = Preconditions.checkNotNull(uri);
        if (log.isInfoEnabled()) {
            log.info("Creating url: [{}]", baseCacheUrl);
        }
    }

    /**
     * Get the base url for this cache server. Actual cache instances will be extensions
     * to this url.
     *
     * @return the base url
     */
    public String getBaseCacheUrl() {
        return baseCacheUrl.toString();
    }

}
