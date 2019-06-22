	
NOTE: there are 2 MAIN methods ...one uses XML Configuration , the other uses Java configuration

NOTE: Start up after starting AMQP application BUT before "HIT RETURN" on AMQP Application

Additional Listeners for AMQP application

TOPIC: Listens on queue purchasesOnline
    NOTE: can be switched to Listen on queue purchasesOnlineClassic - Gets ONLY SUBSET of messages

TOPIC: There is ALSO a TEMPORARY [non-durable] queue created by the client to Listen for purchases.store.#
        NOTE: requires AMQP app to be RUN after the AMQPClient BECAUSE temporary!!!


DIRECT: Listens on queue answerQueue
