package edu.mum.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.mum.domain.RouteItem;
import edu.mum.domain.RouteItem.RouteItemType;


public class WarehouseMessageListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseMessageListener.class);

    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        RouteItem routeItem = null;
		try {
			routeItem = (RouteItem) objectMessage.getObject();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		System.out.println();
        
        if(routeItem.getRouteItemType().equals(RouteItemType.MODERATE_PRICE)) {
        	System.out.println("Moderately Priced Item Message Received: ");
        } else {
        	System.out.println("Highly Priced Item Message Received: ");
        }
        System.out.println("Item Name: " + routeItem.getItem().getName());
    	System.out.println("Item Price: " + routeItem.getItem().getPrice());
    	System.out.println("Auction ID: " + routeItem.getAuctionId());
    	System.out.println();
    }
}
