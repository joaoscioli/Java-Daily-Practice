package org.example.ecommerceapi.domain.service;

import org.example.ecommerceapi.domain.model.User;
import org.example.ecommerceapi.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
