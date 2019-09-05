package com.productsite.api.services;

import com.productsite.api.ApiApplication;
import com.productsite.api.models.ProductModel;
import com.productsite.api.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
@EnableAutoConfiguration
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ProductServiceIT {
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    ProductRepository productRepository;

    private ProductModel productModel1;
    private ProductModel productModel2;
    private ProductModel productModel3;

    @BeforeEach
    void setUp() {
        productModel1 = ProductModel.builder().id(1).code("ABC1").name("Hammer").rating(4.3).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();
        productModel2 = ProductModel.builder().id(2).code("DEF2").name("Leaf Rake").rating(3.3).price(9.99).image("https://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png").build();
        productModel3 = ProductModel.builder().id(3).code("GHI2").name("Controller").rating(4.8).price(10.99).image("https://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png").build();

        productModel1 = productService.createProduct(productModel1);
        productModel2 = productService.createProduct(productModel2);
        productModel3 = productService.createProduct(productModel3);

        assertEquals(3, productRepository.count());
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void createProductSuccess() {
        ProductModel productModel4 = ProductModel.builder().code("MRCN").name("Martian Hammer").rating(4.3).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();

        productService.createProduct(productModel4);

        assertEquals(4, productRepository.count());
    }

    @Test
    public void getProductSuccess() {
        assertEquals(productModel1, productService.getProduct(productModel1.getId()));
    }

    @Test
    public void getProductListSuccess() {
        List<ProductModel> expected = new ArrayList<>();
        expected.add(productModel1);
        expected.add(productModel2);
        expected.add(productModel3);

        assertEquals(expected, productService.getAllProducts());
    }

    @Test
    public void updateProductSuccess() {
        productModel1.setName("Big Hammer");
        productModel1.setPrice(28.65);

        productService.updateProduct(productModel1.getId(), productModel1);

        assertEquals("Big Hammer", productModel1.getName());
        assertEquals(28.65, productModel1.getPrice());
    }

    @Test
    public void deleteProductSuccess() {
        productService.deleteProduct(productModel3.getId());
        assertEquals(2, productRepository.count());

        productService.deleteProduct(productModel2.getId());
        assertEquals(1, productRepository.count());

        productService.deleteProduct(productModel1.getId());
        assertEquals(0, productRepository.count());
    }
}
