package com.springboot.ehcache3.service.impl;


import com.springboot.ehcache3.entity.Product;
import com.springboot.ehcache3.repository.ProductRepository;
import com.springboot.ehcache3.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Cacheable(value = "products", key="#productId")
    public Product getProduct(String productId) {
        logger.info("getting product from db");
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    @CachePut(value = "products", key="#product.id")
    public Product updateProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            product.setId(productOptional.get().getId());
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    @CacheEvict(value = "products", key="#productId")
    public String deleteProduct(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
            return "Product deleted";
        }
        return "Product not found";
    }

}
