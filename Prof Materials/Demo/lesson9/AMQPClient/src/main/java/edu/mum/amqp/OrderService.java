package edu.mum.amqp;

import edu.mum.domain.Order;

public interface OrderService {
    Order getOrder(String stateCode);
}
