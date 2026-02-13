package com.dryfruit.backend.model;

public class ReportResponse {

    private long totalOrders;
    private double totalSales;

    public ReportResponse() {}   // 🔥 Add empty constructor (fixes your error)

    public ReportResponse(long totalOrders, double totalSales) {
        this.totalOrders = totalOrders;
        this.totalSales = totalSales;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
}

