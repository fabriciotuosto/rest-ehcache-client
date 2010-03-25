package org.ehcache.rest.client;

import org.apache.commons.io.IOUtils;
import org.ehcache.rest.client.util.Preconditions;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

class RestClient {

	/**
	 * 
	 */
	private final RestTemplate restTemplate;
	
	/**
	 * 
	 */
	private final ByteArrays helper;

	/**
	 * 
	 * @param restTemplate
	 * @param helper
	 */
	public RestClient(RestTemplate restTemplate,ByteArrays helper) {
		this.restTemplate = Preconditions.checkNotNull(restTemplate);
		this.helper = Preconditions.checkNotNull(helper);
	}
	
	/**
	 * 
	 * @param url
	 * @param value
	 * @return
	 */
	public boolean put(String url,Serializable value){
		try{
			restTemplate.put(url,helper.toByteArray(value));
			return true;
		}catch (HttpClientErrorException e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public boolean delete(String url){
		try{
			restTemplate.delete(url);
			return true;
		}catch (HttpClientErrorException e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public Serializable get(String url){
		try{
			return restTemplate.execute(url, HttpMethod.GET,null,new SerializableObjectResponseExtractor(helper));
		}catch (HttpClientErrorException e) {
			return null;
		}
	}
	
	/**
	 * 
	 *
	 *
	 */
	private static class SerializableObjectResponseExtractor implements ResponseExtractor<Serializable>{

		private final ByteArrays objectHelper;

		SerializableObjectResponseExtractor(ByteArrays helper) {
			objectHelper = helper;
		}

		@Override
		public Serializable extractData(ClientHttpResponse arg0)
				throws IOException {
			try (ByteArrayOutputStream outBytes = new ByteArrayOutputStream()) {
				IOUtils.copy(arg0.getBody(), outBytes);
				return objectHelper.fromByteArray(outBytes.toByteArray());
			}
		}
	}

}
