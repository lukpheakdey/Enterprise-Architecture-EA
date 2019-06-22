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
import edu.mum.rest.RemoteApi;

@Component
public class ProductRestService  implements ProductRestService {

	@Autowired
	RemoteApi restHelper;
	
	public List<Product> findAll() {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/JerseyRestSecurity/rest/products/", HttpMethod.GET, restHelper.getHttpEntity(), Product[].class).getBody());
	}

	public Product findOne(Integer index) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		return (restTemplate.exchange("http://localhost:8080/JerseyRestSecurity/rest/products/"+ index, HttpMethod.GET, restHelper.getHttpEntity(), Product.class).getBody());
	}

	public Product save(Product product) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, restHelper.getHttpHeaders());
		ResponseEntity<Product> result = restTemplate.exchange("http://localhost:8080/JerseyRest/rest/products/", HttpMethod.POST, httpEntity, Product.class);
		return result.getBody();
	}

}
