package com.dryfruit.backend.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    private String id;              // ✅ ADD THIS
    private String customerId;
    private List<OrderItem> items;
    private double totalAmount;
    private LocalDateTime orderDate;

    // ✅ Getters
    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    // ✅ Setters
    public void setId(String id) {          // ✅ THIS FIXES ERROR
        this.id = id;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
