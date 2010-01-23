package org.ehcache.rest.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Fabricio
 * 
 */
public class CacheFactory {

	/**
	 * 
	 */
	private static final String CACHE_BEAN = "cache";
	private static final String REST_CLIENT_BEAN = "restClient";
	private static final String HASH_GENERATOR = "md5hashGenerator";

	/**
	 * 
	 * @return
	 */
	public Cache getCache() {
		return (Cache) getContext().getBean(CACHE_BEAN);
	}

	public Cache getCache(CacheURI location) {
		return new RemoteCache(location, getContext().getBean(REST_CLIENT_BEAN,
				RestClient.class), getContext().getBean(HASH_GENERATOR,
				HashGenerator.class));
	}

	/**
	 * 
	 * @return
	 */
	private ApplicationContext getContext() {
		return ContextHolder.CONTEXT;
	}

	/**
	 * 
	 *
	 */
	private static class ContextHolder {
		private static final String SPRING_CONTEXT_CONFIGURATION = "cache-service.xml";
		private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext(
				new String[] { SPRING_CONTEXT_CONFIGURATION });
	}

}
