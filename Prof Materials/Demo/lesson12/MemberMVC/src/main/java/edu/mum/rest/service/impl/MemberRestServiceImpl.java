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
import edu.mum.domain.Product;
import edu.mum.domain.Member;
import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.MemberRestService;

@Component
public class MemberRestServiceImpl implements MemberRestService {

	@Autowired
	RestHttpHeader restHelper;

	String baseUrl = "http://localhost:8080/MemberRest/members";
	String baseUrlExtended = baseUrl + "/";

	public List<Member> findAll() {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Member[]> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, Member[].class);	
 		List<Member> userList = Arrays.asList(responseEntity.getBody());
		return userList;
	}

	public Member findOne(Long index) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Member> responseEntity = restTemplate.exchange(baseUrlExtended + index, HttpMethod.GET, httpEntity, Member.class);	
		Member member = responseEntity.getBody();
 		return member;
	}

	public Member save(Member member) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Member> httpEntity = new HttpEntity<Member>(member, restHelper.getHttpHeaders());
		member = restTemplate.postForObject(baseUrl, httpEntity, Member.class);
		return null;
	}

}
