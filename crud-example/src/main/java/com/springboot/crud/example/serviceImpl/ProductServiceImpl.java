package com.springboot.crud.example.serviceImpl;

import com.springboot.crud.example.entity.Product;
import com.springboot.crud.example.repository.ProductRepository;
import com.springboot.crud.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product getProduct(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            product.setId(productOptional.get().getId());
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public String deleteProduct(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
            return "Product deleted";
        }
        return "Product not found";
    }

}
