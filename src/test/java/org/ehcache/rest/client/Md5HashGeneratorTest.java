package org.ehcache.rest.client;

import org.ehcache.rest.client.Md5HashGenerator;
import org.ehcache.rest.client.HashGenerator;
import org.ehcache.rest.client.ByteArrays;

public class Md5HashGeneratorTest extends HashGeneratorContractTest {
	private final HashGenerator hasher = new Md5HashGenerator(new ByteArrays());
	
	@Override
	protected HashGenerator getHasher() {
		return hasher;
	}

}
