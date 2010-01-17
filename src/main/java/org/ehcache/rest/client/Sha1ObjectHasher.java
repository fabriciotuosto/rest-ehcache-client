package org.ehcache.rest.client;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;
import org.ehcache.rest.client.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class Sha1ObjectHasher implements HashGenerator {

	/**
	 * Logger for the class.
	 */
	private static final Logger log = LoggerFactory.getLogger(Sha1ObjectHasher.class);
	
	/**
	 * 
	 */
	private final ByteArrays byteArrays;

	/**
	 * 
	 * @param byteArrays
	 */
	public Sha1ObjectHasher(ByteArrays byteArrays) {
		this.byteArrays = Preconditions.checkNotNull(
				byteArrays, "Transform shouldn't be null");
	}
	
	public String generateHash(Serializable object) {
    	Preconditions.checkNotNull(object,"Serializable object should not be null");
    	byte[] sha1 = DigestUtils.sha(byteArrays.toByteArray(object));
    	String sha1String = DigestUtils.shaHex(sha1);
    	if(log.isDebugEnabled()){
    		log.debug("SHA1 hash result is = {}",sha1String);
    	}
    	return sha1String;
	}

}
