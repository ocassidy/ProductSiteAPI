package com.productsite.api.mappers;

import com.productsite.api.entities.Product;
import com.productsite.api.models.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductModel productToProductDTO(Product product);

    Product productDTOToProduct(ProductModel productModel);
}
