package com.example.backend.order.projections;

import com.example.backend.order.OrderConfig;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface OrderView {
    public Long getId();

    public LocalDateTime getOrderPlacedTime();

    public LocalDateTime getPreparationCompletionTime();

    @Value("#{target.receiver.id}")
    public Long getClientId();

    public OrderConfig.Status getStatus();
}
