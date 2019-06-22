
See Installation instructions


                     TOPIC EXCHANGE
                     
Two queues... [ actually 3 - purchasesOnlineClassic is a "subset" on purchasesOnline]
One queue for "built in" consumer purchasesStore
One queue for external  consumer purchasesOnline [ as well as another queue purchasesOnlineClassic]

Has Producers for BOTH  Queues
Has listener on purchasesStore queue

AMQPClient application has listener on purchasesOnline queue [ OR can listen on purchasesOnlineClassic]


                    DIRECT EXCHANGE

Two queues [directQueue, answerQueue]

Has Producer & Consumer[Listener] for directQueue
Has Producer  for answerQueue

 AMQPClient application also has a listener for answerQueue
                