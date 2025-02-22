package org.example.ecommerceapi.domain.repository;

import org.example.ecommerceapi.domain.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByUsername(String email);
    User save(User user);
}
