package com.example.backend.client;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    private Long id;
    private String name;
    private String email;

    private LocalDateTime registration;

    public Client() {

    }

    public Client(Long id, String name, String email, LocalDateTime registration) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registration = registration;
    }

    public Client(String name, String email, LocalDateTime registration) {
        this.name = name;
        this.email = email;
        this.registration = registration;
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
        this.registration = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", registration=" + registration +
                '}';
    }
}
