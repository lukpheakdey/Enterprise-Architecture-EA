package edu.mum.sender;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("topicMessageSender")
public class PubSubMessageSender implements MessageSender {
    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTemplate;
    
    

    public void sendMessage(final Object message, String selector,String value) {
        this.jmsTemplate.send("mumEA.topic",new MessageCreator() {
            public Message createMessage(Session session)
                    throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage((Serializable) message);
                if (selector != null && !selector.isEmpty())
                	objectMessage.setStringProperty(selector, value);
                return objectMessage;
           }

          });
    }
}
