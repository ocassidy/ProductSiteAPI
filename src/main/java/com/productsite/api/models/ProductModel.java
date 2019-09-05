package com.productsite.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    @Min(0)
    private int id;

    @NotNull
    @Column(name = "product_name")
    private String name;

    @NotNull
    private String code;

    @NotNull
    private double price;

    private String description;

    @NotNull
    @Min(1)
    @Max(5)
    private double rating;

    @NotNull
    private String image;
}
