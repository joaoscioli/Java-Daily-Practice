package org.example.ecommerceapi.application.usecase;

import org.example.ecommerceapi.application.dto.ProductDTO;
import org.example.ecommerceapi.application.mapper.ProductMapper;
import org.example.ecommerceapi.domain.model.Product;
import org.example.ecommerceapi.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        product = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }
}
