package org.example.ecommerceapi.application.mapper;

import org.example.ecommerceapi.application.dto.ProductDTO;
import org.example.ecommerceapi.domain.model.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock());
    }

    public static Product toEntity(ProductDTO dto) {
        return new Product(dto.id(), dto.name(), dto.description(), dto.price(), dto.stock());
    }
}
