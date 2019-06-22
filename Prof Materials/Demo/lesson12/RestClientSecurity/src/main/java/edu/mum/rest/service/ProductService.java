package edu.mum.rest.service;

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

@Component
public class ProductService {

	@Autowired
	RestHttpHeader restHttpHeader;
	
	String baseUrl = "http://localhost:8080/JerseyRestSecurity/products";
	String baseUrlExtended = baseUrl + "/";

	public List<Product> read() {
		
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		return Arrays.asList(restTemplate.exchange(baseUrl, 
				        HttpMethod.GET, restHttpHeader.getHttpEntity(), Product[].class).getBody());
		                                //restHttpHeader.getHttpEntity() - get HTTP headers [Authentication; JSON ACCEPT]
	}

	public Product read(Integer index) {
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		return (restTemplate.exchange(baseUrlExtended + index, 
				     HttpMethod.GET, restHttpHeader.getHttpEntity(), Product.class).getBody());
		                                                                      // Returns Product in Body HTTP Message 
	}

	public Product write(Product product) {
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		// HTTPEntity - SEND Headers & Body
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, restHttpHeader.getHttpHeaders());
		restTemplate.postForObject(baseUrl, 
				    httpEntity, Product.class);
                                // Product.class - Response type
 		return null;
	}

}
