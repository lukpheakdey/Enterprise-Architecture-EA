package edu.mum.sender;

public interface MessageSender {
	//Selector is an optional message header property that allows filtering of messages
	// The consumer is configured with a listener to filter on the property 
	// See PubSubMessageSender [ herein] & the JmsTopicToo project from listener
     void sendMessage(Object message, String selector, String value);
}
