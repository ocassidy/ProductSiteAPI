package com.productsite.api.repositories;

import com.productsite.api.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();
    Product findById(int id);
}
