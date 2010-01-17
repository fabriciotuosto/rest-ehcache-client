package org.ehcache.rest.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.io.IOUtils;
import org.ehcache.rest.client.util.Preconditions;


/**
 * Provides an abstraction to transform objects to and from
 * byte arrays
 *
 */
class ObjectHelper {

	private static final byte[] EMPTY_ARRAY = new byte[0];
	
	/**
	 * Transform the given object into a byte array
	 * 
	 * @param o the object to transform to a byte array
	 *   the argument must implements Serializable interface
	 * @return a byte array representation of the serialized object
	 */
	public byte[] objectToByteArray(Serializable o){
		Preconditions.checkNotNull(o);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] transformed = EMPTY_ARRAY;
        try{
        	oos = new ObjectOutputStream(bos);
        	oos.writeObject(o);
        	oos.flush();
        	
        	transformed = bos.toByteArray();
        } catch (IOException e) {
			throw new Error("Unable to transform object to byte array",e);
		}finally{
        	IOUtils.closeQuietly(bos);
        	IOUtils.closeQuietly(oos);
        }
        return transformed;
	}
	
	/**
	 * Transform a byte array into a Object
	 * 
	 * @param array the byte array serialized version of a given
	 * object
	 * @return an Object created from the deserialization of the
	 * byte array
	 */
	public Serializable objectFromByteArray(byte[] array){
		Preconditions.checkNotNull(array);
		Serializable result = null;
		ObjectInputStream ois = null;
		ByteArrayInputStream bis = null;
		try {
			bis = new ByteArrayInputStream(array);
			ois = new ObjectInputStream(bis);
			result = (Serializable) ois.readObject();
		} catch (Exception e) {
			throw new Error("Unable to deserialized object from stream",e);
		}finally{
			IOUtils.closeQuietly(ois);
            IOUtils.closeQuietly(bis);
		}
		return result;
	}
}
