package org.ehcache.rest.client;

import org.ehcache.rest.client.Md5ObjectHasher;
import org.ehcache.rest.client.HashGenerator;
import org.ehcache.rest.client.ByteArrays;

public class Md5ObjectHashserTest extends HashGeneratorContractTest {
	private final HashGenerator hasher = new Md5ObjectHasher(new ByteArrays());
	
	@Override
	protected HashGenerator getHasher() {
		return hasher;
	}

}
