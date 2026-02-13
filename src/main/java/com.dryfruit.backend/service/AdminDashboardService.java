package com.dryfruit.backend.service;

import com.dryfruit.backend.model.Order;
import com.dryfruit.backend.model.OrderItem;
import com.dryfruit.backend.model.AdminDashboardResponse;
import com.dryfruit.backend.repository.OrderRepository;
import com.dryfruit.backend.repository.ProductRepository;
import com.dryfruit.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminDashboardService {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public AdminDashboardService(OrderRepository orderRepo,
                                 ProductRepository productRepo,
                                 UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    public AdminDashboardResponse getDashboard() {

        List<Order> orders = orderRepo.findAll();
        LocalDate today = LocalDate.now();

        long totalOrders = orders.size();

        double totalRevenue = orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();

        long ordersToday = orders.stream()
                .filter(o -> o.getCreatedAt() != null &&
                        o.getCreatedAt().toLocalDate().isEqual(today))
                .count();

        long totalProducts = productRepo.count();
        long totalUsers = userRepo.count();

        Map<String, Long> productSalesCount = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(
                        OrderItem::getProductName,
                        Collectors.counting()
                ));

        Map<String, Double> productRevenueMap = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(
                        OrderItem::getProductName,
                        Collectors.summingDouble(item -> item.getPrice() * item.getQuantity())
                ));

        return new AdminDashboardResponse(
                totalOrders,
                totalRevenue,
                ordersToday,
                totalProducts,
                totalUsers,
                productSalesCount,
                productRevenueMap
        );
    }
}
