package com.example.backend.order;

import com.example.backend.order.projections.OrderView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT p FROM Order p WHERE p.orderPlacedTime is not null and p.preparationCompletionTime is null")
    List<Order> newOrders();

    @Query("SELECT p FROM Order p WHERE p.preparationCompletionTime is not null and p.pickupTime is null")
    List<Order> shipmentReady();

    @Query("SELECT p FROM Order p WHERE p.pickupTime is not null and p.deliveryCompletionTime is null")
    List<Order> inTransport();

    @Query("SELECT p FROM Order p WHERE p.deliveryCompletionTime is not null")
    List<Order> delivered();

    Optional<Order> findOrderById(Long orderId);

    @Query("SELECT p FROM Order p WHERE p.id = ?1")
    Optional<OrderView> findOrderViewById(Long orderId);

    @Query("SELECT o from Order o")
    List<OrderView> allOrders();
    @Query("SELECT o from Order o WHERE o.receiver.id= ?1")
    List<OrderView> findOrdersByClient(Long clientId);

    @Query("SELECT MAX(p.orderPlacedTime) FROM Order p WHERE p.orderPlacedTime IS NOT NULL AND p.preparationCompletionTime IS NULL")
    LocalDateTime lastNewOrderTime();
}
