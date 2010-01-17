package org.ehcache.rest.client;

import org.ehcache.rest.client.ObjectHasher;
import org.ehcache.rest.client.ObjectHelper;
import org.ehcache.rest.client.Sha1ObjectHasher;

public class Sha1ObjectHashserTest extends ObjectHasherTest {

	private final ObjectHasher hasher = new Sha1ObjectHasher(new ObjectHelper());
	
	@Override
	protected ObjectHasher getHasher() {
		return hasher;
	}

}
