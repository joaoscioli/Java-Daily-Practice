package org.example.ecommerceapi.adapter.controller;

import lombok.Getter;
import org.example.ecommerceapi.application.usecase.ProductService;
import org.example.ecommerceapi.domain.model.Product;
import org.example.ecommerceapi.domain.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
         return this.productRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return this.productRepository.findById(id);
    }
}
