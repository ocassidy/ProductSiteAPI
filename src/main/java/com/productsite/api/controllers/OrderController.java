package com.productsite.api.controllers;

import com.productsite.api.entities.Order;
import com.productsite.api.entities.OrderDetails;
import com.productsite.api.mappers.ProductMapper;
import com.productsite.api.models.OrderModel;
import com.productsite.api.models.OrderDetailsModel;
import com.productsite.api.models.OrderStatus;
import com.productsite.api.services.OrderDetailService;
import com.productsite.api.services.OrderService;
import com.productsite.api.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class OrderController {
    private ProductService productService;
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private ProductMapper productMapper;

    public OrderController(ProductService productService, OrderService orderService, OrderDetailService orderDetailService, ProductMapper productMapper) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.productMapper = productMapper;
    }

    @PostMapping(path = "api/orders", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody OrderModel orderModel) {
        List<OrderDetailsModel> orderDetailsModels = orderModel.getOrderDetails();

        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order);

        List<OrderDetails> orderDetails = new ArrayList<>();
        for (OrderDetailsModel orderDetailsModel : orderDetailsModels) {
            orderDetails.add(orderDetailService.create(new OrderDetails(order,
                    productMapper.productDTOToProduct(productService.getProduct(orderDetailsModel.getProduct().getId())),
                    orderDetailsModel.getQuantity())));
        }

        order.setOrderDetails(orderDetails);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }
}
