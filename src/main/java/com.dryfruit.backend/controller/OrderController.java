package com.dryfruit.backend.controller;

import com.dryfruit.backend.model.Order;
import com.dryfruit.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // ============================
    // 1. PLACE ORDER
    // ============================
    @PostMapping("/place/{customerId}")
    public ResponseEntity<Order> placeOrder(@PathVariable String customerId) {
        return ResponseEntity.ok(orderService.placeOrder(customerId));
    }

    // ============================
    // 2. CUSTOMER – ORDER HISTORY
    // ============================
    @GetMapping("/history/{customerId}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable String customerId) {
        return ResponseEntity.ok(orderService.getOrderHistory(customerId));
    }

    // ============================
    // 3. ADMIN – ALL ORDERS
    // ============================
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // ============================
    // 4. ADMIN – UPDATE ORDER STATUS
    // ============================
    @PutMapping("/update-status/{orderId}")
    public ResponseEntity<Order> updateStatus(
            @PathVariable String orderId,
            @RequestParam String status) {

        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

    // ============================
    // 5. SALES REPORT (ADMIN)
    // ============================
    @GetMapping("/sales")
    public ResponseEntity<List<Order>> getSalesReport() {
        return ResponseEntity.ok(orderService.getSalesReport());
    }
}
