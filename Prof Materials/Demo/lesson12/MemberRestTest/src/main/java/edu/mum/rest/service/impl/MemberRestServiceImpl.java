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
import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.MemberRestService;

@Component
public class MemberRestServiceImpl implements MemberRestService {

	@Autowired
	RestHttpHeader restHttpHeader;
	
	public List<Member> findAll() {
		
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/MemberRestJSON/members", HttpMethod.GET, restHttpHeader.getHttpEntity(), Member[].class).getBody());
 	}

	public Member findOne(Long index) {
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
 		return (restTemplate.exchange("http://localhost:8080/MemberRestJSON/members/"+ index, HttpMethod.GET, restHttpHeader.getHttpEntity(), Member.class).getBody());
	}

	public Member save(Member member) {
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		HttpEntity<Member> httpEntity = new HttpEntity<Member>(member, restHttpHeader.getHttpHeaders());
		restTemplate.postForObject("http://localhost:8080/MemberRestJSON/members", httpEntity, Member.class);
		return null;
	}

}
