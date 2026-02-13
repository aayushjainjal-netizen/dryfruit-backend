package com.dryfruit.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardResponse {

    private long totalOrders;
    private double totalRevenue;
    private long ordersToday;

    private long totalProducts;   // NEW
    private long totalUsers;      // NEW

    private Map<String, Long> productSalesCount;
    private Map<String, Double> productRevenueMap;
}
