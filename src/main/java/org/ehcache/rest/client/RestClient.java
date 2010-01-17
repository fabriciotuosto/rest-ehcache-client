package org.ehcache.rest.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

class RestClient {

	private final RestTemplate restTemplate;
	private final ObjectHelper helper;


	public RestClient(RestTemplate restTemplate,ObjectHelper helper) {
		this.restTemplate = restTemplate;
		this.helper = helper;
	}
	
	
	public boolean put(String url,Serializable value){
		try{
			restTemplate.put(url,helper.objectToByteArray(value));
			return true;
		}catch (HttpClientErrorException e) {
			return false;
		}
	}
	
	public boolean delete(String url){
		try{
			restTemplate.delete(url);
			return true;
		}catch (HttpClientErrorException e) {
			return false;
		}
	}
	
	public Serializable get(String url){
		try{
			return restTemplate.execute(url, HttpMethod.GET,null,new SerializableObjectResponseExtractor());
		}catch (HttpClientErrorException e) {
			return null;
		}
	}
	private static class SerializableObjectResponseExtractor implements ResponseExtractor<Serializable>{

		@Override
		public Serializable extractData(ClientHttpResponse arg0)
				throws IOException {
			ByteArrayOutputStream outBytes = null;
			try {
				outBytes = new ByteArrayOutputStream();
				IOUtils.copy(arg0.getBody(), outBytes);
				return new ObjectHelper().objectFromByteArray(outBytes.toByteArray());
			} finally{
				IOUtils.closeQuietly(outBytes);
			}
		}
	}

}
