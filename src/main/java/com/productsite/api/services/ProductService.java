package com.productsite.api.services;

import com.productsite.api.entities.Product;
import com.productsite.api.models.ProductModel;

import java.util.List;

public interface ProductService {
    List<ProductModel> getAllProducts();

    ProductModel createProduct(ProductModel productModel1);

    void updateProduct(int id, ProductModel productModel);

    Product deleteProduct(int id);

    ProductModel getProduct(int id);
}


