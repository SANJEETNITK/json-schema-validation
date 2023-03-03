package com.springboot.junit5example.controller;

import com.springboot.junit5example.entity.Product;
import com.springboot.junit5example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProduct() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public Optional<Product> getProduct(@PathVariable(value = "productId") String productId) {
        return productService.getProduct(productId);
    }
}
