package org.example.ecommerceapi.infrastructure.repository;

import org.example.ecommerceapi.domain.model.Product;
import org.example.ecommerceapi.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long>, ProductRepository {

}
