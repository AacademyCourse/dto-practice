package com.example.demo.service;

import com.example.demo.dto.ClientPasswordUpdate;
import com.example.demo.dto.ClientResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;


import java.util.Set;

public interface ClientService {

    Client saveClient(Client client);
    void updateClient(ClientPasswordUpdate clientPasswordUpdate);
    Client getClient(Long id);
    void deleteClient(Long id);
    ClientResponse login (LoginRequest loginRequest);

    Client findByEmail(String name);
    Set<Client> findAll();
}
