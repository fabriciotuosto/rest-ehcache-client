package org.ehcache.rest.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CacheFactory {
    private static final String CACHE_BEAN = "cache";


    public Cache getCache(){
		return (Cache) getContext().getBean(CACHE_BEAN);
	}
	
		
	private ApplicationContext getContext(){
		return ContextHolder.CONTEXT;
	}
	
	
	private static class ContextHolder{
        private static final String SPRING_CONTEXT_CONFIGURATION = "cache-service.xml";
        private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext(
				new String[] {SPRING_CONTEXT_CONFIGURATION});
    }
	
}
