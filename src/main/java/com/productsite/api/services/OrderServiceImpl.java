package com.productsite.api.services;

import com.productsite.api.entities.Order;
import com.productsite.api.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(int id) {
        return orderRepository.findById(id);
    }

    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());
        return orderRepository.save(order);
    }

    public void update(Order order) {
        this.orderRepository.save(order);
    }
}
