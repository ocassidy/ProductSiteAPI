package com.productsite.api.services;

import com.productsite.api.entities.Order;

public interface OrderService {
    Iterable<Order> getAllOrders();

    Order getOrder(int id);

    Order create(Order order);

    void update(Order order);
}
