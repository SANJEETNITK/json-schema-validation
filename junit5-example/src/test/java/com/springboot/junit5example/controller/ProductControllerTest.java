package com.springboot.junit5example.controller;

import com.springboot.junit5example.entity.Product;
import com.springboot.junit5example.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    ProductController productController = Mockito.spy(ProductController.class);

    @Mock
    private ProductService productService;

    @Test
    public void saveProduct() {
        Product product = new Product();
        product.setId("test123");

        Mockito.when(productService.saveProduct(product)).thenReturn(product);

        assertEquals(product.getId(), productController.saveProduct(product).getId());
    }

}
