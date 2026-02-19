package com.dryfruit.backend.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {

    private String id;
    private String customerId;
    private List<OrderItem> items;
    private double totalAmount;
    private String status;
    private LocalDateTime createdAt;
}
