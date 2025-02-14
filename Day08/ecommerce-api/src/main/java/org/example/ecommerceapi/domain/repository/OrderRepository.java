package org.example.ecommerceapi.domain.repository;

import org.example.ecommerceapi.domain.model.Order;
import org.example.ecommerceapi.domain.model.User;

import java.util.List;

public interface OrderRepository {
    List<Order> findByCustomer(User customer);
    Order save(Order order);

}
