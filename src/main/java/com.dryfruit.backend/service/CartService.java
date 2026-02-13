package com.dryfruit.backend.service;

import com.dryfruit.backend.cart.Cart;
import com.dryfruit.backend.cart.CartItem;
import com.dryfruit.backend.model.Product;
import com.dryfruit.backend.repository.CartRepository;
import com.dryfruit.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    // =======================================
    // 1. ADD ITEM TO CART
    // =======================================
    public Cart addItemToCart(String customerId, CartItem item) {

        // Load existing cart OR create a new one
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElse(new Cart(customerId));

        // Fetch product details from DB
        Product product = productRepository.findById(item.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Secure values
        item.setProductName(product.getName());
        item.setPrice(product.getPrice());

        // Check if item already exists
        boolean exists = false;
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProductId().equals(item.getProductId())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                exists = true;
                break;
            }
        }

        // Add new item
        if (!exists) {
            cart.addItem(item); // addItem already recalculates total
        }

        // Always recalculate total price
        cart.calculateTotal();

        return cartRepository.save(cart);
    }

    // =======================================
    // 2. UPDATE ITEM QUANTITY
    // =======================================
    public Cart updateItemQuantity(String customerId, String productId, int quantity) {

        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.updateQuantity(productId, quantity);
        cart.calculateTotal();

        return cartRepository.save(cart);
    }

    // =======================================
    // 3. REMOVE ITEM FROM CART
    // =======================================
    public Cart removeItem(String customerId, String productId) {

        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.removeItem(productId);
        cart.calculateTotal();

        return cartRepository.save(cart);
    }

    // =======================================
    // 4. GET CART BY CUSTOMER
    // =======================================
    public Optional<Cart> getCart(String customerId) {
        return cartRepository.findByCustomerId(customerId);
    }
}
