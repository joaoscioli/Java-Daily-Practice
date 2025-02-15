package org.example.ecommerceapi.domain.repository;

import org.example.ecommerceapi.domain.model.Cart;
import org.example.ecommerceapi.domain.model.User;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUser(User user);
    Cart save(Cart cart);
}
