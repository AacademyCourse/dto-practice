package com.example.demo.service;

import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Client;
import com.example.demo.exception.RecordNotFoundException;

import javax.management.relation.RoleNotFoundException;

public interface ClientService {
    Client saveClient(ClientRequest client) throws RecordNotFoundException;
    Client updateClient(Client client);
    Client getClient(Long id);
    Client deleteClient(Long id);
    Client login(LoginRequest loginRequest) throws RecordNotFoundException;


}
