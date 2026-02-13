package com.dryfruit.backend.controller;

import com.dryfruit.backend.model.Product;
import com.dryfruit.backend.service.ProductService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // -------------------------
    // USER + ADMIN → View products
    // -------------------------
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Product> getProducts() {
        return service.getAllProducts();
    }

    // -------------------------
    // ADMIN → Add product
    // -------------------------
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    // -------------------------
    // ADMIN → Update product
    // -------------------------
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product updateProduct(@PathVariable String id,
                                 @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

    // -------------------------
    // ADMIN → Delete product
    // -------------------------
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);
        return "Product deleted: " + id;
    }
}
