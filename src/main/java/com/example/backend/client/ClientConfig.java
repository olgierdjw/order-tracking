package com.example.backend.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class ClientConfig {
    @Bean
    CommandLineRunner initExampleClients(ClientRepository clientRepository) {
        return args -> {
            clientRepository.saveAll(
                    List.of(
                            new Client("Olgierd", "olgierdjw@gmail.com"),
                            new Client("Adam", "adam@github.com"),
                            new Client("Ola", "ola@test.pl")
                    )
            );
        };
    }
}
