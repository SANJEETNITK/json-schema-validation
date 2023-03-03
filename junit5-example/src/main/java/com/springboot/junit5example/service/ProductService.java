package com.springboot.junit5example.service;

import com.springboot.junit5example.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    Product saveProduct(Product product);

    List<Product> getProducts();

    Optional<Product> getProduct(String productId);
}
