 
Follow on to EAIDesktop [ which is built upon EAIBusAmqpJms]
Added "option" to send order to STORE [ instead of Warehouse] & send Email to Customer
Store is sent order via JMS


Need to start activemq, AMQP

Need to configure your email sender  in mailContext
Need to declare your recipient in EmailService.java


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

