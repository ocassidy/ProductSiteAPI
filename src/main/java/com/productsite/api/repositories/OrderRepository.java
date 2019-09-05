package com.productsite.api.repositories;

import com.productsite.api.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    Order findById(int id);
}