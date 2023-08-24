package com.example.backend.order;

import com.example.backend.client.Client;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {


    public Order() {

    }

    public Order(Client receiver, Float value) {
        this.receiver = receiver;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id"
    )
    private Client receiver;

    private LocalDateTime orderPlacedTime;
    @PrePersist
    private void onCreate() {
        this.orderPlacedTime = LocalDateTime.now();
    }
    private LocalDateTime preparationCompletionTime;
    private LocalDateTime pickupTime;
    private LocalDateTime deliveryCompletionTime;

    @Transient
    private OrderConfig.Status status;

    private Float value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getReceiver() {
        return receiver;
    }

    public void setReceiver(Client receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getOrderPlacedTime() {
        return orderPlacedTime;
    }


    public LocalDateTime getPreparationCompletionTime() {
        return preparationCompletionTime;
    }

    public void setPreparationCompletionTime(LocalDateTime preparationCompletionTime) {
        this.preparationCompletionTime = preparationCompletionTime;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public LocalDateTime getDeliveryCompletionTime() {
        return deliveryCompletionTime;
    }

    public void setDeliveryCompletionTime(LocalDateTime deliveryCompletionTime) {
        this.deliveryCompletionTime = deliveryCompletionTime;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public OrderConfig.Status getStatus() {
        if (this.deliveryCompletionTime != null)
            return OrderConfig.Status.DELIVERED;
        if (this.pickupTime != null)
            return OrderConfig.Status.SENT;
        if (this.preparationCompletionTime != null)
            return OrderConfig.Status.READY_TO_SHIP;
        return OrderConfig.Status.NEW_ORDER;
    }
}
