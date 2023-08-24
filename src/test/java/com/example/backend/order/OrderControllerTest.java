package com.example.backend.order;

import com.example.backend.order.projections.NewOrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    void newOrder() throws Exception {

        NewOrderDto orderDto = new NewOrderDto();
        orderDto.setClientId(1L);
        float orderValue = 123.56F;
        orderDto.setValue(orderValue);

        mock.perform(post("/orders/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderDto)))
                .andExpect(status().isOk());

        List<Order> allNewOrders = orderRepository.newOrders();
        Optional<Order> addedOrder = allNewOrders.stream()
                .filter(order -> order.getValue() == orderDto.getValue())
                .findFirst();

        assert addedOrder.isPresent();
        assert addedOrder.get().getStatus() == OrderConfig.Status.NEW_ORDER;

    }

}


