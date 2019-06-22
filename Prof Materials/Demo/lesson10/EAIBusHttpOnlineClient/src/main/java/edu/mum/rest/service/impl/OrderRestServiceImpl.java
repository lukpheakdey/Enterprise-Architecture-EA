package edu.mum.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import edu.mum.domain.Order;
import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.OrderRestService;

@Component
public class OrderRestServiceImpl  implements OrderRestService {

	@Autowired
	RestHttpHeader remoteApi;
	
 
	public Order save(Order order) {
		try {

		RestTemplate restTemplate = remoteApi.getRestTemplate();
		HttpEntity<Order> httpEntity = new HttpEntity<Order>(order, remoteApi.getHttpHeaders());
//		restTemplate.postForObject("http://localhost:8080/EAIBusFinal/onlineOrder", 
		restTemplate.postForObject("http://localhost:8080/EAIBusHttpOnline/onlineOrder", 
				        httpEntity, Order.class);
                                     // Order.class - Response type
 		}
		catch (RestClientException exce) {
			System.out.println("ERROR: " + exce.getMessage());
		}
		return null;
	}

}
