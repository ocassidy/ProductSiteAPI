package com.productsite.api.services;

import com.productsite.api.entities.OrderDetails;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderDetailService {

    OrderDetails create(@NotNull(message = "The products for order cannot be null.") @Valid OrderDetails orderDetails);
}
