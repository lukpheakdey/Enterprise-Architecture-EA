package edu.mum.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.mum.domain.RouteOrder;


public class BataviaMessageListener implements MessageListener {
 
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        RouteOrder routeOrder = null;
		try {
			routeOrder = (RouteOrder) objectMessage.getObject();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("BATAVIA - Message received: " );
        System.out.println("         product Name: "  + 
        		routeOrder.getOrder().getItems().get(0).getProduct().getName());

    }
}
