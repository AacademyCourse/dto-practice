package com.example.demo.service;

import com.example.demo.entity.Client;

import java.util.Set;

public interface ClientService {
    Set<Client> getClients();
    Client getClient(Long id);
    Client addClient(Client client);
    Client modifyClient(Long id, Client client);
    void deleteClient(Long id);
}
