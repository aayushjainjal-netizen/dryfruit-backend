package com.dryfruit.backend.controller;

import com.dryfruit.backend.cart.Cart;
import com.dryfruit.backend.cart.CartItem;
import com.dryfruit.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // ============================
    // 1️⃣ ADD ITEM TO CART
    // ============================
    @PostMapping("/add/{customerId}")
    public Cart addItemToCart(
            @PathVariable String customerId,
            @RequestBody CartItem item
    ) {
        System.out.println("ADD TO CART CALLED FOR CUSTOMER: " + customerId);
        return cartService.addItemToCart(customerId, item);
    }

    // ============================
    // 2️⃣ GET CART
    // ============================
    @GetMapping("/{customerId}")
    public Cart getCart(@PathVariable String customerId) {
        System.out.println("GET CART CALLED FOR CUSTOMER: " + customerId);
        return cartService.getCart(customerId).orElse(null);

    }

    // ============================
    // 3️⃣ UPDATE ITEM QUANTITY
    // ============================
    @PutMapping("/update/{customerId}")
    public Cart updateItemQuantity(
            @PathVariable String customerId,
            @RequestParam String productId,
            @RequestParam int quantity
    ) {
        System.out.println("UPDATE QTY CALLED FOR CUSTOMER: " + customerId);
        return cartService.updateItemQuantity(customerId, productId, quantity);
    }

    // ============================
    // 4️⃣ REMOVE ITEM FROM CART
    // ============================
    @DeleteMapping("/remove/{customerId}")
    public Cart removeItemFromCart(
            @PathVariable String customerId,
            @RequestParam String productId
    ) {
        System.out.println("REMOVE ITEM CALLED FOR CUSTOMER = " + customerId);
        return cartService.removeItem(customerId, productId);
    }
}
