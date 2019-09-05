package com.productsite.api.controllers;

import com.productsite.api.models.ProductModel;
import com.productsite.api.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ProductController {
    private ProductService productService;
    private static final String UPDATE_SUCCESS = "Product Updated";
    private static final String DELETE_SUCCESS = "Product Deleted";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable int id) {
        ProductModel productModel = productService.getProduct(id);

        return new ResponseEntity<>(productModel, OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getProducts() {
        List<ProductModel> productList = productService.getAllProducts();

        return new ResponseEntity<>(productList, OK);
    }

    @ResponseBody
    @PostMapping("/products")
    ResponseEntity createProduct(@RequestBody @Valid ProductModel productModel) {
        productService.createProduct(productModel);

        return new ResponseEntity<>(productModel, CREATED);
    }

    @ResponseBody
    @PutMapping(path = "/products/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity updateProduct(@PathVariable int id, @RequestBody @Valid ProductModel productModel) {
        productService.updateProduct(id, productModel);

        return new ResponseEntity<>(UPDATE_SUCCESS, OK);
    }

    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity deletePlayer(@PathVariable int id) {
        productService.deleteProduct(id);

        return new ResponseEntity<>(DELETE_SUCCESS, OK);
    }
}
