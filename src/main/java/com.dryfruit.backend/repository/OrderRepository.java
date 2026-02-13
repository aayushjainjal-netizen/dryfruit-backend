package com.dryfruit.backend.repository;

import com.dryfruit.backend.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByCustomerId(String customerId);   // for order history

    // for admin: get all orders (already in MongoRepository)
}
