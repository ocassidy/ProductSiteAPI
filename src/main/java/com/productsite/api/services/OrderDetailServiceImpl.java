package com.productsite.api.services;

import com.productsite.api.entities.OrderDetails;
import com.productsite.api.repositories.OrderDetailRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetails create(OrderDetails orderDetails) {
        return this.orderDetailRepository.save(orderDetails);
    }
}
