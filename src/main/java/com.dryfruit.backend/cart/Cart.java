package com.dryfruit.backend.cart;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "cart")
public class Cart {

    @Id
    private String id;

    private String customerId;

    private List<CartItem> items = new ArrayList<>();

    private double totalPrice;

    public Cart(String customerId) {
        this.customerId = customerId;
    }

    // ===============================
    // ADD ITEM
    // ===============================
    public void addItem(CartItem item) {
        if (items == null) items = new ArrayList<>();
        items.add(item);
        calculateTotal();
    }

    // ===============================
    // REMOVE ITEM
    // ===============================
    public void removeItem(String productId) {
        if (items != null) {
            items.removeIf(i -> i.getProductId().equals(productId));
            calculateTotal();
        }
    }

    // ===============================
    // UPDATE QUANTITY
    // ===============================
    public void updateQuantity(String productId, int quantity) {
        if (items != null) {
            for (CartItem item : items) {
                if (item.getProductId().equals(productId)) {
                    item.setQuantity(quantity);
                    break;
                }
            }
            calculateTotal();
        }
    }

    // ===============================
    // TOTAL PRICE CALCULATION
    // ===============================
    public void calculateTotal() {
        if (items == null) {
            totalPrice = 0;
            return;
        }

        totalPrice = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }
}
