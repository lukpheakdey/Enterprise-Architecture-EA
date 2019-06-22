package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.mum.domain.Product;
import edu.mum.domain.Product;
import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.ProductRestService;

@Component("MVC")
public class ProductRestServiceImpl  implements ProductRestService {

	@Autowired
	RestHttpHeader restHelper;
	
	String baseUrl = "http://localhost:8080/MemberRest/products";
	String baseUrlExtended = baseUrl + "/";

	public List<Product> findAll() {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Product[]> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, Product[].class);	
 		List<Product> userList = Arrays.asList(responseEntity.getBody());
		return userList;
	}

	public Product findOne(Long index) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Product> responseEntity = restTemplate.exchange(baseUrlExtended + index, HttpMethod.GET, httpEntity, Product.class);	
		Product product = responseEntity.getBody();
 		return product;
	}

	public Product save(Product product) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, restHelper.getHttpHeaders());
		product = restTemplate.postForObject(baseUrl, httpEntity, Product.class);
		return null;
	}

}
