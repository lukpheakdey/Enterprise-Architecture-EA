
Follow on  to EAIBusAmqpJms

Added Orders from "online site"
Broadcast to warehouse over JMS
See Slides...

Need to start activemq, AMQP

NOTES FROM EAIBusAmqpJms

AMQP/RabbitMQ - sends Order...
This is done from AMQP project from Lesson 9

The EAI Bus configures AMQP/RabbitMQ -just like AMQPClient from Lesson 9
The Bus implements the Listener thru a AMQP Adapter
The Order is then TRANSFORMed into a RouteOrder 
This RouteOrder is broadcast thru the JMS adapter to the Warehouse Listener...


 