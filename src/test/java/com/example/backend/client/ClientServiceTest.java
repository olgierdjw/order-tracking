package com.example.backend.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientServiceTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ClientRepository clientRepository;


    @Test
    void newClient() throws Exception {
        mock.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "name": "username1",
                                "email": "username1@test.com"
                                }
                                                      """))
                .andExpect(status().isOk());
        Optional<Client> client = clientRepository.findClientByEmail("username1@test.com");
        assert client.isPresent();
    }


}