package com.dryfruit.backend.controller;

import com.dryfruit.backend.model.AdminDashboardResponse;
import com.dryfruit.backend.service.AdminDashboardService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService dashboardService;

    @GetMapping("/dashboard")
    public AdminDashboardResponse getDashboard() {
        return dashboardService.getDashboard();
    }
}
