package com.example.backend.client;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    final private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public void addNewClient(@RequestBody Client client) {
        Optional<Client> alreadyRegistered = clientRepository.findClientByEmail(client.getEmail());
        if (alreadyRegistered.isPresent()) {
            throw new IllegalStateException("email already taken");
        }
        clientRepository.save(client);
    }

    public void deleteClient(Long clientId) {
        if(clientRepository.existsById(clientId)) {
            throw new IllegalArgumentException("invalid user id");
        }
    }

    public Optional<Client> getClient(Long clientId) {
        return clientRepository.findClientById(clientId);
    }

    public Long getTotalClients() {
        return clientRepository.count();
    }
}
