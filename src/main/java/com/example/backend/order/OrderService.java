package com.example.backend.order;

import com.example.backend.client.Client;
import com.example.backend.client.ClientService;
import com.example.backend.order.projections.NewOrderDto;
import com.example.backend.order.projections.OrderView;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.backend.order.OrderConfig.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrderRepository orderRepository;
    ClientService clientService;

    public OrderService(OrderRepository orderRepository, ClientService clientService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
    }

    public List<OrderView> allOrders() {
        return orderRepository.allOrders();
    }

    public void newOrder(NewOrderDto newOrderDto) {
        Optional<Client> client = clientService.getClient(newOrderDto.getClientId());
        if (client.isEmpty()) {
            throw new IllegalArgumentException("client not found");
        }
        Order newOrder = new Order();
        newOrder.setReceiver(client.get());
        newOrder.setValue(newOrderDto.getValue());
        orderRepository.save(newOrder);

    }

    public List<OrderView> getByClient(Long clientId) {
        return orderRepository.findOrdersByClient(clientId);
    }

    public Order getDetails(Long orderId) {
        Optional<Order> order = orderRepository.findOrderById(orderId);
        if (order.isEmpty()) {
            throw new IllegalArgumentException("order not found");
        }
        return order.get();
    }

    public List<Order> getByStatus(Status status) {
        switch (status) {

            case NEW_ORDER -> {
                return orderRepository.newOrders();
            }
            case READY_TO_SHIP -> {
                return orderRepository.shipmentReady();
            }
            case SENT -> {
                return orderRepository.inTransport();
            }
            case DELIVERED -> {
                return orderRepository.delivered();
            }
        }
        return List.of();
    }

    @Transactional
    public Optional<OrderView> nextStatus(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findOrderById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("order not found");
        }
        Order order = optionalOrder.get();
        Status currentStatus = order.getStatus();

        if (currentStatus == Status.DELIVERED) {
            throw new IllegalArgumentException("order already delivered");
        }

        LocalDateTime now = LocalDateTime.now();

        switch (currentStatus) {
            case NEW_ORDER -> order.setPreparationCompletionTime(now);
            case READY_TO_SHIP -> order.setPickupTime(now);
            case SENT -> order.setDeliveryCompletionTime(now);
        }

        return orderRepository.findOrderViewById(orderId);
    }

    public Long getTotalOrders() {
        return orderRepository.count();
    }

    public LocalDateTime getLastOrderTime() {
        return orderRepository.lastNewOrderTime();
    }
}
