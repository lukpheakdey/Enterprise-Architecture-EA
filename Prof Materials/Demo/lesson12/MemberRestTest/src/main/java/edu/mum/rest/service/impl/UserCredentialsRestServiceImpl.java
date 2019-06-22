package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.mum.domain.Product;
import edu.mum.domain.UserCredentials;
import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.UserCredentialsRestService;

@Component
public class UserCredentialsRestServiceImpl implements UserCredentialsRestService {

	@Autowired
	RestHttpHeader remoteApi;
	
	public List<UserCredentials> findAll() {
		
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/MemberRestJSON/userCredentials", HttpMethod.GET, remoteApi.getHttpEntity(), UserCredentials[].class).getBody());
	}

	public UserCredentials findOne(String index) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		UserCredentials uc =  (restTemplate.exchange("http://localhost:8080/MemberRestJSON/userCredentials/"+ index, HttpMethod.GET, remoteApi.getHttpEntity(), UserCredentials.class).getBody());
		return uc;
	}

	public UserCredentials save(UserCredentials product) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		HttpEntity<UserCredentials> httpEntity = new HttpEntity<UserCredentials>(product, remoteApi.getHttpHeaders());
		restTemplate.postForObject("http://localhost:8080/MemberRestJSON/userCredentials", httpEntity, Product.class);
		return null;
	}

}
