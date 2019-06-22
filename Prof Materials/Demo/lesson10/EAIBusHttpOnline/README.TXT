
Follow on to EAIDesktop

Note: does not include the Store section from EAIBus

  Added Orders from Online web site
  Done with Http
  
  Need to run on Tomcat Server

Need to start activemq, AMQP

Need to send order from EAIBusHttpOnlineClient [ with correct URL(see README.TXT)]


NOTES FROM EAIDesktop

	Added Orders from "Customer Service"
	Broadcast to warehouse over JMS
	See Slides...
 
NOTES FROM EAIBusAmqpJms

	Phone order comes from Headquarters
	AMQP/RabbitMQ - sends Order...
	This is done from AMQPPhone project  
	
	The EAI Bus configures AMQP/RabbitMQ -just like AMQPClient from Lesson 9 [only uses purchasesPhone queue
	The Bus implements the Listener thru a AMQP Adapter
	The Order is then TRANSFORMed into a RouteOrder 
	This RouteOrder is broadcast thru the JMS adapter to the Warehouse Listener...

