package com.example.backend.dashboard;

import com.example.backend.client.ClientService;
import com.example.backend.order.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DashboardService {
    private final ClientService clientService;
    private final OrderService orderService;

    public DashboardService(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    public DashboardData calculateStatistics() {
        Long totalClients = clientService.getTotalClients();
        Long totalOrders = orderService.getTotalOrders();
        LocalDateTime lastOrderTime= orderService.getLastOrderTime();
        return new DashboardData(totalClients, totalOrders, lastOrderTime);
    }
}

