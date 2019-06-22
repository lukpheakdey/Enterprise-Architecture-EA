package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.mum.domain.Member;
import edu.mum.domain.Member;
import edu.mum.rest.RemoteApi;

@Component
public class MemberRestService {

	@Autowired
	RemoteApi remoteApi;
	
	public List<Member> findAll() {
		
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/JerseyRestSecurity/rest/products/", HttpMethod.GET, remoteApi.getHttpEntity(), Member[].class).getBody());
	}

	public Member findOne(Integer index) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		return (restTemplate.exchange("http://localhost:8080/JerseyRestSecurity/rest/products/"+ index, HttpMethod.GET, remoteApi.getHttpEntity(), Member.class).getBody());
	}

	public Member save(Member product) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		HttpEntity<Member> httpEntity = new HttpEntity<Member>(product, remoteApi.getHttpHeaders());
		ResponseEntity<Member> result = restTemplate.exchange("http://localhost:8080/JerseyRest/rest/products/", HttpMethod.POST, httpEntity, Member.class);
		return result.getBody();
	}

}
