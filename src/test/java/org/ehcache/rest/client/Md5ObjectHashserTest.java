package org.ehcache.rest.client;

import org.ehcache.rest.client.Md5ObjectHasher;
import org.ehcache.rest.client.ObjectHasher;
import org.ehcache.rest.client.ObjectHelper;

public class Md5ObjectHashserTest extends ObjectHasherTest {
	private final ObjectHasher hasher = new Md5ObjectHasher(new ObjectHelper());
	
	@Override
	protected ObjectHasher getHasher() {
		return hasher;
	}

}
