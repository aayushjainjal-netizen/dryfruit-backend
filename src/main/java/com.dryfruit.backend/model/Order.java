package com.dryfruit.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String customerId;
    private List<OrderItem> items;
    private double totalAmount;
    private String status; // PENDING, PAID, CANCELLED, DELIVERED

    private LocalDateTime createdAt;   // 🔥 IMPORTANT

}
