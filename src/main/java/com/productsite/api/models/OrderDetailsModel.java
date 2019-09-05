package com.productsite.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.productsite.api.entities.OrderDetailsPK;
import com.productsite.api.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsModel {
    @JsonIgnore
    private OrderDetailsPK pk;

    private Integer quantity;

    private Product product;
}
