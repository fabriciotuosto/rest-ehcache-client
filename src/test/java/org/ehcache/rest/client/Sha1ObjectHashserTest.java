package org.ehcache.rest.client;

import org.ehcache.rest.client.HashGenerator;
import org.ehcache.rest.client.ByteArrays;
import org.ehcache.rest.client.Sha1ObjectHasher;

public class Sha1ObjectHashserTest extends HashGeneratorContractTest {

	private final HashGenerator hasher = new Sha1ObjectHasher(new ByteArrays());
	
	@Override
	protected HashGenerator getHasher() {
		return hasher;
	}

}
