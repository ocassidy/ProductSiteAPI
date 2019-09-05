package com.productsite.api.services;
import com.productsite.api.controllers.ProductController;
import com.productsite.api.models.ProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductServiceUT {
    private ProductModel productModel1;
    private ProductModel productModel2;

    @InjectMocks
    private ProductController productController;

    @MockBean
    private ProductServiceImpl productService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        productModel1 = ProductModel.builder().id(1).code("ABC1").name("Hammer").rating(4.3).price(19.99).image("https://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png").build();
        productModel2 = ProductModel.builder().id(2).code("DEF2").name("Leaf Rake").rating(3.3).price(9.99).image("https://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png").build();
    }

    @Test
    public void getPlayer() throws Exception {
        when(productService.getProduct(1)).thenReturn(productModel1);

        mockMvc.perform(get("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Hammer")));
    }

    @Test
    public void getAllPlayers() throws Exception {
        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(productModel1);
        productModelList.add(productModel2);

        when(productService.getAllProducts()).thenReturn(productModelList);

        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", is("Hammer")))
                .andExpect(jsonPath("$.[1].name", is("Leaf Rake")));
    }

    @Test
    public void createIsSuccessful() throws Exception {
        when(productService.createProduct(productModel1)).thenReturn(productModel1);

        String requestBody = "{\"id\": 1,\"name\": \"Leaf Rake\",\n" +
                "\"code\": \"LR123\",\n" +
                "\"releaseDate\": \"1990-01-01T00:00:00.000+0000\",\n" +
                "\"price\": 21.99,\n" +
                "\"description\": \"Leaf Rake\",\n" +
                "\"rating\": 5,\n" +
                "\"image\": \"https://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png\"}";

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Leaf Rake")));
    }

    @Test
    public void updateIsSuccessful() throws Exception {
        String requestBody = "{\"id\": 1,\"name\": \"Leaf Rake\",\n" +
                "\"code\": \"LR123\",\n" +
                "\"releaseDate\": \"1990-01-01T00:00:00.000+0000\",\n" +
                "\"price\": 21.99,\n" +
                "\"description\": \"Leaf Rake\",\n" +
                "\"rating\": 5,\n" +
                "\"image\": \"https://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png\"}";

        mockMvc.perform(put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteProductSuccess() throws Exception {
        String requestBody = "{\"id\": 1,\"name\": \"Leaf Rake\",\n" +
                "\"code\": \"LR123\",\n" +
                "\"releaseDate\": \"1990-01-01T00:00:00.000+0000\",\n" +
                "\"price\": 21.99,\n" +
                "\"description\": \"Leaf Rake\",\n" +
                "\"rating\": 5,\n" +
                "\"image\": \"https://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png\"}";

        mockMvc.perform(delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }
}
