package com.example.backend.dashboard;

import java.time.LocalDateTime;

public class DashboardData {
    private Long totalClients;
    private Long totalOrders;
    private LocalDateTime lastOrderTime;

    public DashboardData() {

    }

    public DashboardData(Long totalClients, Long totalOrders, LocalDateTime lastOrderTime) {
        this.totalClients = totalClients;
        this.totalOrders = totalOrders;
        this.lastOrderTime = lastOrderTime;
    }

    public Long getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(Long totalClients) {
        this.totalClients = totalClients;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public LocalDateTime getLastOrderTime() {
        return lastOrderTime;
    }

    public void setLastOrderTime(LocalDateTime lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }
}
