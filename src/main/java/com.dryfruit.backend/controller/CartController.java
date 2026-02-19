package com.dryfruit.backend.controller;

import com.dryfruit.backend.cart.Cart;
import com.dryfruit.backend.cart.CartItem;
import com.dryfruit.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin
public class CartController {

    private final CartService cartService;

    // ✅ Get Logged-in User Cart
    @GetMapping
    public Cart getCart(Authentication authentication) {
        String email = authentication.getName();
        return cartService.getCartByEmail(email);
    }

    // ✅ Add Item
    @PostMapping("/add")
    public Cart addToCart(@RequestBody CartItem item, Authentication authentication) {
        String email = authentication.getName();
        return cartService.addToCart(email, item);
    }

    // ✅ Remove Item
    @DeleteMapping("/remove/{productId}")
    public Cart removeFromCart(@PathVariable String productId, Authentication authentication) {
        String email = authentication.getName();
        return cartService.removeFromCart(email, productId);
    }
}
