package com.example.backend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        // instead of creating new Service use dependency injection
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> allClients() {
        return clientService.getClients();
    }

    @PostMapping
    public void addNewClient(Client client) {
        clientService.addNewClient(client);
    }


    @DeleteMapping("/{client-id}")
    public void deleteClient(@PathVariable("client-id") Long clientId) {
        clientService.deleteClient(clientId);
    }
}
