package com.springboot.crud.example.controller;

import com.springboot.crud.example.entity.Product;
import com.springboot.crud.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/products")
    public List<Product> save(@RequestBody List<Product> products) {
        return productService.saveProducts(products);
    }

    @GetMapping("/product/{id}")
    public Product getProducts(@PathVariable("id") int productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") int productId) {
        return productService.deleteProduct(productId);
    }

}
