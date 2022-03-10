package com.example.bitfactory.api;

import com.example.bitfactory.product.command.CreateProductCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper;

    public ProductControllerTest() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void getAllProduct() throws Exception {
        mockMvc.perform(get("/products")
                .header("Authorization", "Bearer 123456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addProduct() throws Exception {
        CreateProductCommand command = new CreateProductCommand("葡萄", "sweet", BigDecimal.TEN, "http://baidu.com", 100L);
        String request = objectMapper.writeValueAsString(command);
        mockMvc.perform(post("/products")
                .header("Authorization", "Bearer 123456")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
