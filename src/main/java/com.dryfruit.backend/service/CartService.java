package com.dryfruit.backend.service;

import com.dryfruit.backend.cart.Cart;
import com.dryfruit.backend.cart.CartItem;
import com.dryfruit.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart getCartByEmail(String email) {
        return cartRepository.findByCustomerId(email)
                .orElseGet(() -> cartRepository.save(new Cart(email)));
    }

    public Cart addToCart(String email, CartItem item) {
        Cart cart = getCartByEmail(email);
        cart.addItem(item);
        return cartRepository.save(cart);
    }

    public Cart removeFromCart(String email, String productId) {
        Cart cart = getCartByEmail(email);
        cart.removeItem(productId);
        return cartRepository.save(cart);
    }
}
