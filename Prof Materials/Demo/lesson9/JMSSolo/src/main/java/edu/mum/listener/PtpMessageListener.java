package edu.mum.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.mum.domain.Product;

public class PtpMessageListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(PtpMessageListener.class);

    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        Product productInfo = null;
		try {
			productInfo = (Product) objectMessage.getObject();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		logger.info("         Product Name: "  + productInfo.getName());
		logger.info("                 Description: "  + productInfo.getDescription());
		logger.info("                 Price: "  + productInfo.getPrice());

		}
}
