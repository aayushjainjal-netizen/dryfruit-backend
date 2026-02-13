package com.dryfruit.backend.controller;

import com.dryfruit.backend.model.Order;
import com.dryfruit.backend.repository.OrderRepository;
import com.dryfruit.backend.service.AdminDashboardService;
import com.dryfruit.backend.model.AdminDashboardResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final OrderRepository orderRepository;
    private final AdminDashboardService dashboardService;

    public AdminController(OrderRepository orderRepository, AdminDashboardService dashboardService) {
        this.orderRepository = orderRepository;
        this.dashboardService = dashboardService;
    }

    @PutMapping("/order/{id}/status")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable String id,
            @RequestParam String status
    ) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        orderRepository.save(order);

        return ResponseEntity.ok("Order status updated to: " + status);
    }


}
