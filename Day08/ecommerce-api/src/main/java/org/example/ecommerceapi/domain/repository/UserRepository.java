package org.example.ecommerceapi.domain.repository;

import org.example.ecommerceapi.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String email);
    User save(User user);
}
