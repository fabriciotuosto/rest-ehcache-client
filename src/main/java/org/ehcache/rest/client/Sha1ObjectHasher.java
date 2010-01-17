package org.ehcache.rest.client;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;
import org.ehcache.rest.client.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class Sha1ObjectHasher implements ObjectHasher {

	/**
	 * Logger for the class.
	 */
	private static final Logger log = LoggerFactory.getLogger(Sha1ObjectHasher.class);
	
	private final ObjectHelper transformer;

	public Sha1ObjectHasher(ObjectHelper transformer) {
		this.transformer = Preconditions.checkNotNull(
				transformer, "Transform shouldn't be null");
	}
	
	public String hashObject(Serializable object) {
    	Preconditions.checkNotNull(object,"Serializable object should not be null");
    	byte[] sha1 = DigestUtils.sha(transformer.objectToByteArray(object));
    	String sha1String = DigestUtils.shaHex(sha1);
    	if(log.isDebugEnabled()){
    		log.debug("SHA1 hash result is = {}",sha1String);
    	}
    	return sha1String;
	}

}
