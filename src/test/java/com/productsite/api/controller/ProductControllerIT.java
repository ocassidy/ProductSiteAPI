package com.productsite.api.controller;

import com.productsite.api.models.ProductModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ProductControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createProductSuccess() {
        ProductModel productModel = ProductModel.builder().id(1).code("ABC1").name("Hammer").rating(4.3).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();
        HttpEntity<ProductModel> request = new HttpEntity<>(productModel);
        ResponseEntity<String> response = restTemplate.postForEntity("/products", request, String.class);

        assertEquals(CREATED, response.getStatusCode());
    }

    @Test
    public void getProductSuccess() {
        ProductModel productModel1 = ProductModel.builder().id(1).code("ABC1").name("Hammer").rating(4.3).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();
        HttpEntity<ProductModel> requestDTO1 = new HttpEntity<>(productModel1);
        ResponseEntity<String> responseDTO1 = restTemplate.postForEntity("/products", requestDTO1, String.class);

        assertEquals(CREATED, responseDTO1.getStatusCode());


        ResponseEntity<ProductModel> response = restTemplate.exchange(
                "/products/1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ProductModel>() {});

        assertEquals(productModel1, response.getBody());
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    public void getAllProductsSuccess() {
        ProductModel productModel1 = ProductModel.builder().id(1).code("ABC1").name("Hammer").rating(4.3).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();
        HttpEntity<ProductModel> requestDTO1 = new HttpEntity<>(productModel1);
        ResponseEntity<String> responseDTO1 = restTemplate.postForEntity("/products", requestDTO1, String.class);

        assertEquals(CREATED, responseDTO1.getStatusCode());

        ProductModel productModel2 = ProductModel.builder().id(2).code("DEF2").name("Pickaxe").rating(1.2).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();
        HttpEntity<ProductModel> requestDTO2 = new HttpEntity<>(productModel2);
        ResponseEntity<String> responseDTO2 = restTemplate.postForEntity("/products", requestDTO2, String.class);

        assertEquals(CREATED, responseDTO2.getStatusCode());

        ProductModel productModel3 = ProductModel.builder().id(3).code("GHI3").name("Computer").rating(2.4).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();
        HttpEntity<ProductModel> requestDTO3 = new HttpEntity<>(productModel3);
        ResponseEntity<String> responseDTO3 = restTemplate.postForEntity("/products", requestDTO3, String.class);

        assertEquals(CREATED, responseDTO3.getStatusCode());

        List<ProductModel> productList = new ArrayList<>();
        productList.add(productModel1);
        productList.add(productModel2);
        productList.add(productModel3);

        ResponseEntity<List<ProductModel>> response = restTemplate.exchange(
                "/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductModel>>() {});

        assertEquals(productList, response.getBody());
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    public void updateProductSuccess() {
        ProductModel productModel1 = ProductModel.builder().id(1).code("ABC1").name("Hammer").rating(4.3).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();
        HttpEntity<ProductModel> requestCreate = new HttpEntity<>(productModel1);
        ResponseEntity<String> responseCreate = restTemplate.postForEntity("/products", requestCreate, String.class);

        assertEquals(CREATED, responseCreate.getStatusCode());

        ProductModel productModelUpdated = ProductModel.builder().id(1).code("ABC1").name("Big Hammer").rating(4.3).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();
        HttpEntity<ProductModel> requestUpdate = new HttpEntity<>(productModelUpdated);
        ResponseEntity<String> responseUpdate = restTemplate.exchange(
                "/products/1",
                HttpMethod.PUT,
                requestUpdate,
                String.class);

        assertEquals("Product Updated", responseUpdate.getBody());
        assertEquals(OK, responseUpdate.getStatusCode());
    }

    @Test
    public void deleteProductSuccess() {
        ProductModel productModel1 = ProductModel.builder().id(1).code("ABC1").name("Hammer").rating(4.3).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();
        HttpEntity<ProductModel> requestDTO1 = new HttpEntity<>(productModel1);
        ResponseEntity<String> responseDTO1 = restTemplate.postForEntity("/products", requestDTO1, String.class);

        assertEquals(CREATED, responseDTO1.getStatusCode());

        ResponseEntity<String> response = restTemplate.exchange(
                "/products/1",
                HttpMethod.DELETE,
                null,
                String.class);

        assertEquals("Product Deleted", response.getBody());
        assertEquals(OK, response.getStatusCode());
    }
}
