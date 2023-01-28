package com.springboot.crud.example.service;

import com.springboot.crud.example.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product saveProduct(Product product);

    List<Product> saveProducts(List<Product> products);

    Product getProduct(int productId);

    List<Product> getAllProducts();

    Product updateProduct(Product product);

    String deleteProduct(int productId);

}
