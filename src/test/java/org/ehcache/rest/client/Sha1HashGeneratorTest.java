package org.ehcache.rest.client;

public class Sha1HashGeneratorTest extends HashGeneratorContractTest {

	private final HashGenerator hasher = new Sha1HashGenerator(new ByteArrays());
	
	@Override
	protected HashGenerator getHasher() {
		return hasher;
	}

}
