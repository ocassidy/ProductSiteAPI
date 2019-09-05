package com.productsite.api.repositories;

import com.productsite.api.entities.OrderDetails;
import com.productsite.api.entities.OrderDetailsPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetails, OrderDetailsPK> {
}