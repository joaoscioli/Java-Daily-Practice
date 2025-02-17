package org.example.ecommerceapi.domain.service;

import org.example.ecommerceapi.domain.model.Order;
import org.example.ecommerceapi.domain.model.Product;
import org.example.ecommerceapi.domain.model.Role;
import org.example.ecommerceapi.domain.model.User;

public class OrderDomainService {
    public boolean validateOrder(Order order) {
        return !order.getProducts().isEmpty();
    }

    public boolean canUserPlaceOrder(User user) {
        return  user != null && user.getRole() == Role.CUSTOMER;
    }
}
