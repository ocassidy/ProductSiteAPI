package com.productsite.api.services;

import com.productsite.api.entities.Product;
import com.productsite.api.models.ProductModel;
import com.productsite.api.mappers.ProductMapper;
import com.productsite.api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductModel getProduct(int id) {
        return productMapper.productToProductDTO(productRepository.findById(id));
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
    }

    public ProductModel createProduct(ProductModel productModel) {
        return saveAndReturnDTO(productMapper.productDTOToProduct(productModel));
    }

    public void updateProduct(int id, ProductModel productModel) {
        productModel.setId(id);

        saveAndReturnDTO(productMapper.productDTOToProduct(productModel));
    }

    private ProductModel saveAndReturnDTO(Product product) {
        productRepository.save(product);

        return productMapper.productToProductDTO(product);
    }

    public Product deleteProduct(int id) {
        Product playerFound = productRepository.findById(id);
        productRepository.deleteById(id);
        return playerFound;
    }
}
