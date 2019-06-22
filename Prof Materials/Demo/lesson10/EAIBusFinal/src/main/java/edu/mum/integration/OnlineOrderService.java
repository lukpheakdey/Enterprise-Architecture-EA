package edu.mum.integration;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import edu.mum.domain.Order;

@Component
public class OnlineOrderService {


     public Message<Order> post(Message<Order> orderMessage) {
    	 System.out.println("POST IT Message: " + 
                  orderMessage.getPayload().getItems().get(0).getProduct().getName());
        return orderMessage;
    }
}
	
	