package com.dryfruit.backend.service;

import com.dryfruit.backend.model.Order;
import com.dryfruit.backend.model.OrderItem;
import com.dryfruit.backend.repository.CartRepository;
import com.dryfruit.backend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public OrderService(CartRepository cartRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    // =====================================
    // 1️⃣ PLACE ORDER
    // =====================================
    public Order placeOrder(String customerId) {

        var cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = new Order();
        order.setCustomerId(customerId);

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(ci -> new OrderItem(
                        ci.getProductId(),
                        ci.getProductName(),
                        ci.getPrice(),
                        ci.getQuantity()
                ))
                .collect(Collectors.toList());

        order.setItems(orderItems);

        double total = orderItems.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        order.setTotalAmount(total);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    // =====================================
    // 2️⃣ CUSTOMER ORDER HISTORY
    // =====================================
    public List<Order> getOrderHistory(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    // =====================================
    // 3️⃣ ADMIN - ALL ORDERS
    // =====================================
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // =====================================
    // 4️⃣ ADMIN - UPDATE ORDER STATUS
    // =====================================
    public Order updateOrderStatus(String orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status.toUpperCase());
        return orderRepository.save(order);
    }

    // =====================================
    // 5️⃣ SALES REPORT
    // =====================================
    public List<Order> getSalesReport() {
        return orderRepository.findAll();
    }
}
