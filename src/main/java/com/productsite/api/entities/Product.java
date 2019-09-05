package com.productsite.api.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @NotNull
    @Column(name = "product_name")
    private String name;

    @NotNull
    @Column(name = "product_code")
    private String code;

    @NotNull
    @Column(name = "product_price")
    private double price;

    @Column(name = "product_description")
    private String description;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "product_rating")
    private double rating;

    @NotNull
    @Column(name = "product_image")
    private String image;
}
