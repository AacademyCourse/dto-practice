package com.example.demo.service;

import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.exception.StatusNotFoundException;

import javax.management.relation.RoleNotFoundException;
import java.util.Set;

public interface ClientService {
    Set<Client> getClients();
    Client getClient(Long id);
    Client login(LoginRequest loginRequest) throws RecordNotFoundException;
    Client saveClient(ClientRequest client) throws StatusNotFoundException;
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);
}
