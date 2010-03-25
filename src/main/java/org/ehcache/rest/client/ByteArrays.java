package org.ehcache.rest.client;

import org.ehcache.rest.client.util.Preconditions;

import java.io.*;


/**
 * Provides an abstraction to transform objects to and from
 * byte arrays
 */
class ByteArrays {

    /**
     * Transform the given object into a byte array
     *
     * @param o the object to transform to a byte array
     *          the argument must implements Serializable interface
     * @return a byte array representation of the serialized object
     */
    public byte[] toByteArray(Serializable o) {
        Preconditions.checkNotNull(o);
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(o);
            oos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new Error("Unable to transform object to byte array", e);
        }
    }

    /**
     * Transform a byte array into a Object
     *
     * @param array the byte array serialized version of a given
     *              object
     * @return an Object created from the deserialization of the
     * byte array
     */
    public Serializable fromByteArray(byte[] array) {
        Preconditions.checkNotNull(array);
        try (ByteArrayInputStream bis = new ByteArrayInputStream(array);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (Serializable) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Error("Unable to transform byte array to object", e);
        }
    }
}
