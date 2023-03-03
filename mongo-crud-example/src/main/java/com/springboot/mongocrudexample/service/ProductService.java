package com.springboot.mongocrudexample.service;

import com.springboot.mongocrudexample.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product saveProduct(Product product);
}
