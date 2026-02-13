package com.dryfruit.backend.service;

import com.dryfruit.backend.model.Product;
import com.dryfruit.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product updateProduct(String id, Product updated) {
        Product p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        p.setName(updated.getName());
        p.setPrice(updated.getPrice());
        p.setStock(updated.getStock());
        p.setCategory(updated.getCategory());
        p.setDescription(updated.getDescription());
        p.setImageUrl(updated.getImageUrl());
        p.setWeight(updated.getWeight());
        p.setDiscount(updated.getDiscount());

        return repo.save(p);
    }

    public void deleteProduct(String id) {
        repo.deleteById(id);
    }
}
