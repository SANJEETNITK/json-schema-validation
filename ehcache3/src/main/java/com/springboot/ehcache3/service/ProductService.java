package com.springboot.ehcache3.service;

import com.springboot.ehcache3.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product saveProduct(Product product);

    Product getProduct(String productId);

    Product updateProduct(Product product);

    String deleteProduct(String productId);
}
