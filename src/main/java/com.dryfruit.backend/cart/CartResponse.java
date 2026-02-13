package com.dryfruit.backend.cart;

public class CartResponse {

    private String productId;
    private int quantity;

    public CartResponse(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
