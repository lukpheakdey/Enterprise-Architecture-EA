
Message sent from HQ to Warehouse

AMQP/RabbitMQ - sends Order... FROM HQ
This is done from AMQPPhone project  

The EAI Bus configures AMQP/RabbitMQ -just like AMQPClient from Lesson 9 [only PurchasePhone queue]
The Bus implements the Listener thru an AMQP Adapter

The Order is then TRANSFORMed into a RouteOrder 
This RouteOrder is broadcast thru the JMS adapter to the Warehouse Listener...

Need to start activemq,AMQP


