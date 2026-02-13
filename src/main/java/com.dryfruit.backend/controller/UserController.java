package com.dryfruit.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    // USER + ADMIN can access
    @GetMapping("/profile")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String getProfile() {
        return "User Profile Data";
    }

    // USER + ADMIN can access
    @GetMapping("/my-orders")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String getOrders() {
        return "Your Orders List";
    }

    // USER ONLY (not admin)
    @PostMapping("/update-profile")
    @PreAuthorize("hasAuthority('USER')")
    public String updateProfile() {
        return "User Profile Updated Successfully!";
    }
}

