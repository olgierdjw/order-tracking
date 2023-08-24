package com.example.backend.order;

import com.example.backend.client.Client;
import com.example.backend.client.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class OrderConfig {
    public static enum Status {
        NEW_ORDER, READY_TO_SHIP, SENT, DELIVERED

    }

    @Bean
    CommandLineRunner addExampleOrders(OrderRepository orderRepository, ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(new Client("client-1", "cl1@email.com", LocalDateTime.now()));
            Client client1 = clientRepository.findClientByEmail("cl1@email.com").get();

            clientRepository.save(new Client("client-2", "cl2@email.com", LocalDateTime.now()));
            Client client2 = clientRepository.findClientByEmail("cl2@email.com").get();

            orderRepository.saveAll(
                    List.of(new Order(client1, 14.30F),
                            new Order(client1, 1033.49F),
//                            new Order(client2, 222.22F),
                            new Order(client2, 12.00F)
                    )
            );
        };
    }
}
