package edu.mum.sender;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("messageSender")
public class SimpleMessageSender implements MessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final Object message) {
        this.jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createObjectMessage((Serializable) message);
            }
          });
    }
}
