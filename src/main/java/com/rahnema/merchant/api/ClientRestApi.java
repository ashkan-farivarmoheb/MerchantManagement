package com.rahnema.merchant.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rahnema.merchant.exception.ClientRestException;

@Service
public class ClientRestApi {

	@Value("${address.host}")
	private String address;
	
	@Autowired
	private RestTemplate restTemplate;

	public Map<HttpStatus, String> post(String url, String data) throws ClientRestException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
		HttpEntity<String> request = new HttpEntity<String>(data, headers);

		try {			
			
			ResponseEntity<String>  r = restTemplate.exchange(address + url, HttpMethod.POST, request, String.class);
			Map<HttpStatus, String> res = new HashMap<HttpStatus, String>();
			res.put(r.getStatusCode(), r.getBody());
			
			return res;
		} catch (Exception e) {
			throw new ClientRestException(e.getMessage());
		}
	}

	public Map<HttpStatus, String> put(String url, String data) throws ClientRestException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
		HttpEntity<String> request = new HttpEntity<String>(data, headers);

		try {			
			ResponseEntity<String>  r = restTemplate.exchange(address + url, HttpMethod.PUT, request, String.class);
			Map<HttpStatus, String> res = new HashMap<HttpStatus, String>();
			res.put(r.getStatusCode(), r.getBody());
			
			return res;
		} catch (Exception e) {
			throw new ClientRestException(e.getMessage());
		}
	}
	
}