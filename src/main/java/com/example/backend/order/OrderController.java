package com.example.backend.order;

import com.example.backend.order.projections.NewOrderDto;
import com.example.backend.order.projections.OrderView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<OrderView> getAll() {
        return orderService.allOrders();
    }

    @PostMapping("/new")
    public void newOrder(@RequestBody NewOrderDto request) {
        this.orderService.newOrder(request);
    }

    @GetMapping("status-update/{id}")
    public Optional<OrderView> nextStatus(@PathVariable Long id) {
        return orderService.nextStatus(id);
    }

    @GetMapping("/new")
    public List<Order> newOrders() {
        return orderService.getByStatus(OrderConfig.Status.NEW_ORDER);
    }

    @GetMapping("/ready")
    public List<Order> ready() {
        return orderService.getByStatus(OrderConfig.Status.READY_TO_SHIP);
    }

    @GetMapping("/sent")
    public List<Order> inTransport() {
        return orderService.getByStatus(OrderConfig.Status.SENT);
    }

    @GetMapping("/delivered")
    public List<Order> delivered() {
        return orderService.getByStatus(OrderConfig.Status.DELIVERED);
    }

    @GetMapping("status/{id}")
    public Order details(@PathVariable Long id) {
        return orderService.getDetails(id);
    }

    @GetMapping("client/{id}")
    public List<OrderView> byClient(@PathVariable Long id) {
        return orderService.getByClient(id);
    }
}
