package org.ehcache.rest.client;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;
import org.ehcache.rest.client.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @see ObjectHasher
 */
class Md5ObjectHasher implements ObjectHasher {

	/**
	 * Logger for the class.
	 */
	private static final Logger log = LoggerFactory.getLogger(Md5ObjectHasher.class);
	
	private final ObjectHelper transformer;
	
	public Md5ObjectHasher(ObjectHelper transformer) {
		this.transformer = (ObjectHelper) Preconditions.checkNotNull(
				transformer, "Transform shouldn't be null");
	}

    /**
     * Hash the passed object, producing a String which can be used as
     * a key. The key produced will be unique (as far as possible) across
     * different input objects. On the other hand, hashing 2 equal objects
     * will always produce the same key.
     *
     * @param object the object to be hashed
     * @return the hashed version of the object
     */
    public String hashObject(Serializable object) {
    	Preconditions.checkNotNull(object,"Serializable object should not be null");
    	byte[] md5 = DigestUtils.md5(transformer.objectToByteArray(object));
    	String md5String = DigestUtils.md5Hex(md5);
    	if(log.isDebugEnabled()){
    		log.debug("MD5 hash result is = {}",md5String);
    	}
    	return md5String;
    }
}
