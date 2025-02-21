package org.example.ecommerceapi.adapter.persistence;

import org.example.ecommerceapi.domain.model.Product;
import org.example.ecommerceapi.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long>, ProductRepository {
}
