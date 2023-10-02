package com.springboot.ehcache3.controller;

import com.springboot.ehcache3.entity.Product;
import com.springboot.ehcache3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") String productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") String productId) {
        return productService.deleteProduct(productId);
    }

}
