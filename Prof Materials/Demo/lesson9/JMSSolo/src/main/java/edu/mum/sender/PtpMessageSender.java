package edu.mum.sender;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("messageSender")
public class PtpMessageSender implements MessageSender {
    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessage(final Object message, String selector, String value) {
        this.jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createObjectMessage((Serializable) message);
            }
          });
    }
}
